package ecse321.group12.tamas.controller;

public class DepartmentRegisteredException extends Exception {
	private static final long serialVersionUID = -5633928562703837868L;
    public DepartmentRegisteredException(String errorMessage) {
        super(errorMessage);
    }
}
