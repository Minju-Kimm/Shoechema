package JAVA_Files.product;

import JAVA_Files.MainPage;
import JAVA_Files.auth.UserProfile;

import java.util.Scanner;

public class ProductMenu {

    public static void showSearchMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+————————————————————————————+");
        System.out.println("[메뉴]");
        System.out.println("1. 상품을 선택해 상세 정보를 보기");
        System.out.println("2. 신상품을 보기");
        System.out.println("3. 상품을 검색하기");
        System.out.println("+————————————————————————————+");
        System.out.println("4. 마이 페이지");
        System.out.println("5. 메인 페이지");
        System.out.println("+————————————————————————————+");

        System.out.print("메뉴 번호 입력 -> ");
        int menu = scanner.nextInt();

        switch (menu) {
            case 1 -> ShowDetail.showDetail();
            case 2 -> ShowNewProducts.showNewProducts();
            case 3 -> SearchProducts.searchProducts();
            case 4 -> UserProfile.showUserProfile();
            case 5 -> MainPage.main();
            default -> System.out.println("번호를 잘못 입력하셨습니다.");
        }
        showSearchMenu();
    }

    public static void showDetailMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("+————————————————————————————+");
        System.out.println("[메뉴]");
        System.out.println("1. 신상품을 보기");
        System.out.println("2. 상품을 검색하기");
        System.out.println("3. 다른 상품의 상세 정보를 보기");
        System.out.println("+————————————————————————————+");
        System.out.println("4. 마이 페이지");
        System.out.println("5. 메인 페이지");
        System.out.println("+————————————————————————————+");

        System.out.print("메뉴 번호 입력 -> ");
        int menu = scanner.nextInt();

        switch (menu) {
            case 1 -> ShowNewProducts.showNewProducts();
            case 2 -> SearchProducts.searchProducts();
            case 3 -> ShowDetail.showDetail();
            case 4 -> UserProfile.showUserProfile();
            case 5 -> MainPage.main();
            default -> System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해주세요.");
        }
        showDetailMenu();
    }
}
