import java.io.PrintWriter;

/*
 * Class methods adapted from CPSC 319 lecture notes
 * Node class for a binary search tree
 */
public class Node {
	private char opcode;
	private int studentnum;
	private String lastname;
	private String department;
	private String program;
	private int year;
	private Node left, right, parent;
	
	public Node() {
		setParent(setLeft(setRight(null)));
	}
	public Node(char opcd, int snum, String lnam, String dep, String prog, int yr) {
		this(opcd, snum, lnam, dep, prog, yr, null, null, null);
	}
	public Node(char opcd, int snum, String lnam, String dep, String prog, int yr, Node lt, Node rt, Node p) {
		setOpcode(opcd);
		setStudentnum(snum);
		setLastname(lnam);
		setDepartment(dep);
		setProgram(prog);
		setLeft(lt);
		setRight(rt);
		setParent(p);
	}
	public char getOpcode() {
		return opcode;
	}
	public void setOpcode(char opcode) {
		this.opcode = opcode;
	}
	public int getStudentnum() {
		return studentnum;
	}
	public void setStudentnum(int studentnum) {
		this.studentnum = studentnum;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Node getLeft() {
		return left;
	}
	public Node setLeft(Node left) {
		this.left = left;
		return left;
	}
	public Node getRight() {
		return right;
	}
	public Node setRight(Node right) {
		this.right = right;
		return right;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public void visit(PrintWriter pw) {
		pw.println(getLastname().trim() + " ");
	}
}
