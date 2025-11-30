public class MenuHandler {

    public void showMainMenu() {
        System.out.println("┌────────────────────┐");
        System.out.println("│ MAIN MENU          │");
        System.out.println("├────────────────────┤");
        System.out.println("│ 1. Add New Task    │");
        System.out.println("│ 2. View All Tasks  │");
        System.out.println("│ 3. Update Task     │");
        System.out.println("│ 4. Delete Task     │");
        System.out.println("│ 5. Search Tasks    │");
        System.out.println("│ 6. Filter Tasks    │");
        System.out.println("│ 7. View Statistics │");
        System.out.println("│ 8. Exit            │");
        System.out.println("└────────────────────┘");
        System.out.print("Choose option: ");
    }

    public void showFilterMenu() {
        System.out.println("\nFilter Options:");
        System.out.println("1. Filter by Priority");
        System.out.println("2. Filter by Status");
        System.out.print("Choose filter type: ");
    }

    public void showExitMessage() {
        System.out.println("Goodbye!");
    }

    public void showInvalidOptionMessage() {
        System.out.println("Invalid option. Try again.");
    }
}