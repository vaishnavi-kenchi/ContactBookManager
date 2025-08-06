package app;

import entity.ContactBookEntity;
import service.ContactBookService;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ContactBookService service = new ContactBookService();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Contact Book Manager ---");
            System.out.println("1. Add new Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. View All Contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    long phone = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter email: ");
                    String Email = sc.nextLine();

                    service.CreateNewContact(name, phone, Email);
                    break;

                case 2:
                    System.out.print("Enter contact ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new phone number: ");
                    long newPhone = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();

                    service.updateContact(updateId, newName, newPhone, newEmail);
                    break;

                case 3:
                    System.out.print("Enter Contact ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    service.DeleteContact(deleteId);
                    break;

                case 4:
                    List<ContactBookEntity> cbm = service.findAllContacts();
                    if (cbm.isEmpty()) {
                        System.out.println("No Contacts Found.");
                    } else {
                        System.out.println("--- All Contacts ---");
                        for (ContactBookEntity c : cbm) {
                            System.out.println("Contact ID: " + c.getContactid() + ", Name: " + c.getname()  ", Phone: " + c.getphone() +", Email: " + c.getEmail());
                        }
                    }
                    break;

                case 5:
                    running = false;
                    service.ShutDown();
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }

        sc.close();
    }
}
