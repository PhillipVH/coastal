package za.ac.sun.cs.coastal.instrument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceMethodVisitor;

import za.ac.sun.cs.coastal.COASTAL;

public class HeavyAdapter extends ClassVisitor {

	private final COASTAL coastal;

	private final String name;

	private final StringWriter swriter = new StringWriter();

	private final PrintWriter pwriter = new PrintWriter(swriter);

	public HeavyAdapter(COASTAL coastal, String name, ClassVisitor cv) {
		super(Opcodes.ASM7, cv);
		this.coastal = coastal;
		this.name = name;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		int triggerIndex = coastal.findTrigger(this.name + "." + name, desc);
		boolean isStatic = (access & Opcodes.ACC_STATIC) > 0;
		int argCount = countArguments(desc);
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
		if (mv != null) {
			Printer p = new Textifier(Opcodes.ASM6) {

				@Override
				public void visitCode() {
					pwriter.println("------ " + name + " ------");
				}

				@Override
				public void visitMethodEnd() {
					print(pwriter);
				}
			};
			mv = new TraceMethodVisitor(mv, p);
		}
		if (mv != null) {
			mv = new HeavyMethodAdapter(coastal, mv, this.name, triggerIndex, name, isStatic, argCount);
		}
		return mv;
	}

	private static final Pattern ALL_PARAMS_PATTERN = Pattern.compile("(\\(.*?\\))");
	private static final Pattern PARAMS_PATTERN = Pattern.compile("(\\[?)(B|C|Z|S|I|J|F|D|(:?L[^;]+;))");

	private static int countArguments(String desc) {
		Matcher m0 = ALL_PARAMS_PATTERN.matcher(desc);
		if (!m0.find()) {
			return 0;
		}
		Matcher m1 = PARAMS_PATTERN.matcher(m0.group(1));
		int count = 0;
		while (m1.find()) {
			count++;
		}
		return count;
	}

	public void showInstrumentation() {
		coastal.getLog().info(swriter.toString());
	}

}
