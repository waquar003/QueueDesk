package com.waquar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the starting ticket number");
            int ticketNumber = sc.nextInt();
            System.out.println("Enter the number of customer to server");
            int customersNumber = sc.nextInt();

            int vipLane = 0;
            int priorityRecheck = 0;

            for (int i = 1; i <= customersNumber; i++) {
                System.out.printf("Now serving ticket #%d\n", ticketNumber+i-1);

                if (i % 5 == 0)    vipLane++;
                if (i % 10 == 0) priorityRecheck++;
            }

            System.out.println("Total Served: " + customersNumber);
            System.out.println("Total VIP Lane: " + vipLane);
            System.out.println("Total Priority Recheck: " + priorityRecheck);

            sc.close();
    }
}