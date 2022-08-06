package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt() + 1;

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt() + 1;
        System.out.println();

        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");

        char[][] places = new char[rows][seats];
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                if (i == 0 && j == 0) {
                    places[i][j] = ' ';
                    continue;
                } else if (i == 0) {
                    places[i][j] = (char) (j + '0');
                } else if (j == 0) {
                    places[i][j] = (char) (i + '0');
                } else if (i > 0 && j > 0) {
                    places[i][j] = 'S';
                }
            }
        }

        int action;
        while ((action = scanner.nextInt()) != 0) {
            switch (action) {
                case 1:
                    showTheSeats(places);
                    break;
                case 2:
                    buyATicket(places);
                    break;
                case 3:
                    statistics(places);
                    break;
            }

            System.out.println("1. Show the seats\n" +
                            "2. Buy a ticket\n" +
                            "3. Statistics\n" +
                            "0. Exit");
        }
    }

    public static void showTheSeats(char[][] places) {
        System.out.println("Cinema:");
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                System.out.print(places[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyATicket(char[][] places) {
        Scanner scanner = new Scanner(System.in);
        int rows = places.length - 1;
        int seats = places[0].length - 1;


        System.out.println("\nEnter a row number:");
        int row = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();
        System.out.println();

        if (row < 1 || row > rows || seat < 1 || seat > seats) {
            System.out.println("Wrong input!");
            buyATicket(places);
        } else if (places[row][seat] == 'S') {
            places[row][seat] = 'B';

            if (rows * seats < 60) {
                System.out.println("Ticket price: $" + 10 + "\n");
            } else if (row <= rows / 2) {
                System.out.println("Ticket price: $" + 10 + "\n");
            } else {
                System.out.println("Ticket price: $" + 8 + "\n");
            }
        }
        else {
            System.out.println("That ticket has already been purchased!");
            buyATicket(places);
        }
    }


    public static void statistics(char[][] places) {
        int sellCounter = 0, income = 0;
        int rows = places.length - 1, seats = places[0].length - 1;

        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                if (places[i][j] == 'B') {
                    sellCounter++;
                    if (i <= rows / 2)
                        income += 10;
                    else
                        income += 8;
                }
            }
        }

        double per = (double) (sellCounter * 100) / (double) (rows * seats);
        int totalIncome = (rows * seats < 60) ? (rows * seats * 10) : rows / 2 * seats * 10 + (rows - (rows / 2)) * seats * 8;

        System.out.printf("\nNumber of purchased tickets: %d" + '\n' +
                          "Percentage: %.2f%%\n" +
                          "Current income: $%d\n" +
                          "Total income: $%d\n\n", sellCounter, per, income, totalIncome);

    }
    
}