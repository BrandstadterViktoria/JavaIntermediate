package linkedList;

public class EmployeeDoublyLinkedList {

    private EmployeeNode head;
    private EmployeeNode tail;
    private int size;

    public EmployeeDoublyLinkedList() {
    }

    public void addtoTheFront(Employee employee) {
        EmployeeNode newNode = new EmployeeNode(employee);

        if (head == null) {
            tail = newNode;
        }else {
            head.setPrevious(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        size++;

    }

    public void addToTheEnd(Employee employee) {
        EmployeeNode newNode = new EmployeeNode(employee);

        if(tail == null){
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        tail = newNode;
        size++;
    }

    public void print() {
        EmployeeNode current = head;
        System.out.println("HEAD -->");
        while (current != null) {
            System.out.println(current);
            System.out.println("<=>");
            current = current.getNext();
        }
        System.out.println("null");
    }

    public boolean addBefore(Employee newEmployee, Employee existingEmployee) {


        return false;
    }

}


