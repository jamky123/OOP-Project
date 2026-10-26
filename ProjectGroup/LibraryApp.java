import javax.swing.*;

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library(); 

        while (true) {
            String[] menuOptions = {
                "1. Add Book", 
                "2. Add Magazine",
                "3. Add DVD",
                "4. Add Member",
                "5. Borrow Item",
                "6. Return Item",
                "7. Search Item by ID",
                "8. Search Items by Title",
                "9. List All Items",
                "10. List All Members",
                "11. Remove Member",
                "12. Add Featured Item",
                "13. List Featured Items",
                "14. List Loan Records",
                "15. Exit"
            };

            String choice = (String) JOptionPane.showInputDialog(
                null,
                "Select Menu:",
                "Library Application",
                JOptionPane.QUESTION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
            );

            if (choice == null) {
                continue;
            }

            if (choice.startsWith("15")) {
                int confirm = JOptionPane.showOptionDialog(
                    null,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Yes", "No"},
                    "No"
                );
                if (confirm == JOptionPane.YES_OPTION) break;
                else continue;
            }

            try {
                int selectedOption = Integer.parseInt(choice.substring(0, choice.indexOf('.')));

                switch (selectedOption) {
                    case 1 -> {
                        String itemId = JOptionPane.showInputDialog("Enter Item ID:");
                        String title = JOptionPane.showInputDialog("Enter Title:");
                        String author = JOptionPane.showInputDialog("Enter Author:");
                        String isbn = JOptionPane.showInputDialog("Enter ISBN:");
                        library.addBook(itemId, title, author, isbn);
                        JOptionPane.showMessageDialog(null, "Book added successfully!");
                    }
                    case 2 -> {
                        String itemId = JOptionPane.showInputDialog("Enter Item ID:");
                        String title = JOptionPane.showInputDialog("Enter Title:");
                        int issueNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter Issue Number:"));
                        String publicationMonth = JOptionPane.showInputDialog("Enter Publication Month:");
                        library.addMagazine(itemId, title, issueNumber, publicationMonth);
                        JOptionPane.showMessageDialog(null, "Magazine added successfully!");
                    }
                    case 3 -> {
                        String itemId = JOptionPane.showInputDialog("Enter Item ID:");
                        String title = JOptionPane.showInputDialog("Enter Title:");
                        int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Duration (minutes):"));
                        String director = JOptionPane.showInputDialog("Enter Director:");
                        library.addDVD(itemId, title, duration, director);
                        JOptionPane.showMessageDialog(null, "DVD added successfully!");
                    }
                    case 4 -> {
                        String memberId = JOptionPane.showInputDialog("Enter Member ID:");
                        String name = JOptionPane.showInputDialog("Enter Name:");
                        String[] memberTypes = {"STUDENT", "FACULTY", "COMMUNITY", "PREMIUM"};
                        String memberTypeStr = (String) JOptionPane.showInputDialog(
                            null,
                            "Select Member Type:",
                            "Member Type",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            memberTypes,
                            memberTypes[0]
                        );
                        MemberType memberType = MemberType.valueOf(memberTypeStr);
                        library.addMember(memberId, name, memberType);
                        JOptionPane.showMessageDialog(null, "Member added successfully!");
                    }
                    case 5 -> {
                        String memberId = JOptionPane.showInputDialog("Enter Member ID:");
                        String itemId = JOptionPane.showInputDialog("Enter Item ID:");
                        library.borrowItem(memberId, itemId);
                        JOptionPane.showMessageDialog(null, "Item borrowed successfully!");
                    }
                    case 6 -> {
                        String memberId = JOptionPane.showInputDialog("Enter Member ID:");
                        String itemId = JOptionPane.showInputDialog("Enter Item ID:");
                        library.returnItem(memberId, itemId);
                        JOptionPane.showMessageDialog(null, "Item returned successfully!");
                    }
                    case 7 -> {
                        String itemId = JOptionPane.showInputDialog("Enter Item ID:");
                        LibraryItem item = library.searchItemById(itemId);
                        if (item != null) {
                            JOptionPane.showMessageDialog(null, item.toString(), "Item Found", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Item not found!", "Search Result", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    case 8 -> {
                        String title = JOptionPane.showInputDialog("Enter Title to search:");
                        java.util.ArrayList<LibraryItem> results = library.searchItemsByTitle(title);
                        if (results.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No items found!", "Search Result", JOptionPane.WARNING_MESSAGE);
                        } else {
                            StringBuilder resultStr = new StringBuilder("=== SEARCH RESULTS ===\n");
                            for (LibraryItem item : results) {
                                resultStr.append(item.toString()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, resultStr.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    case 9 -> {
                        String itemsData = library.listAllItems();
                        JOptionPane.showMessageDialog(null, itemsData, "Catalog", JOptionPane.INFORMATION_MESSAGE);
                    }
                    case 10 -> {
                        String membersData = library.listAllMembers();
                        JOptionPane.showMessageDialog(null, membersData, "Members", JOptionPane.INFORMATION_MESSAGE);
                    }
                    case 11 -> {
                        String memberId = JOptionPane.showInputDialog("Enter Member ID to remove:");
                        library.removeMember(memberId);
                        JOptionPane.showMessageDialog(null, "Member removed successfully!");
                    }
                    case 12 -> {
                        String itemId = JOptionPane.showInputDialog("Enter Item ID to add as featured:");
                        LibraryItem item = library.searchItemById(itemId);
                        if (item != null) {
                            library.addFeaturedItem(item);
                            JOptionPane.showMessageDialog(null, "Item added to featured items!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 13 -> {
                        String featuredData = library.listFeaturedItems();
                        JOptionPane.showMessageDialog(null, featuredData, "Featured Items", JOptionPane.INFORMATION_MESSAGE);
                    }
                    case 14 -> {
                        String recordsData = library.listLoanRecords();
                        JOptionPane.showMessageDialog(null, recordsData, "Loan Records", JOptionPane.INFORMATION_MESSAGE);
                    }
                    default -> JOptionPane.showMessageDialog(null, "Invalid selection!");
                }

            } catch (MaxLoanExceededException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Loan Error", JOptionPane.ERROR_MESSAGE);
            } catch (ItemUnavailableException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Item Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number input!", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
        JOptionPane.showMessageDialog(null, "Thank you for using Library App!");
    }
}