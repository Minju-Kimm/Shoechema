package JAVA_Files.product;

import JAVA_Files.MainPage;
import JAVA_Files.auth.UserProfile;
import JAVA_Files.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//상품 상세보기 페이지
public class ShowDetail {
    static Scanner scanner = new Scanner(System.in);

    public static void showDetail() {
        System.out.print("상품 번호 입력 (0번 입력시 뒤로 이동) -> ");
        int shoesId = scanner.nextInt();
        scanner.nextLine();
        ShowDetail showDetail = new ShowDetail();
        //SQL문 작성
        String sql = "" +
                "SELECT s.name, s.price, s.release_date, so.quantity, so.shoes_option_id, sz.size_number " +
                "FROM Shoes s " +
                "JOIN ShoesOptions so ON s.shoes_id = so.shoes_id " +
                "JOIN Sizes sz ON so.size_id = sz.size_id " +
                "WHERE s.shoes_id=" + shoesId;


        //PreparedStatement 지정
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            //SQL 문 실행 후 ResultSet 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                //데이터 행 읽고 객체 생성
                Shoes shoes = new Shoes();
                shoes.setName(rs.getString("name")); //신발 이름
                shoes.setPrice(rs.getInt("price")); //신발 가격
                shoes.setReleaseDate(rs.getDate("release_date")); //신발 출시일
                shoes.setQuantity(rs.getInt("quantity")); //재고
                shoes.setSizeNum(rs.getInt("size_number")); //사이즈
                shoes.setShoesOptId(rs.getInt("shoes_option_id")); //shoes_option_id -> 제품 주문하기로 이어지는 매개변수

                //콘솔에 출력
                System.out.println("제품명 : " + shoes.getName());
                System.out.println("가격 : " + shoes.getPrice() + "원");
                System.out.println("사이즈 : " + shoes.getSizeNum());
                System.out.println("제품 넘버 : " + shoes.getShoesOptId());
                System.out.println("------------------------------");
                System.out.println("출시일 : " + shoes.getReleaseDate());
                System.out.println("------------------------------");
            }

            showDetail.showMenu(); // 메뉴로 이동

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    void showMenu() {
        while (true) {
            System.out.println("주문하기 -> [Y/N]");
            String result = scanner.nextLine();

            if (result.equals("Y")) {
                System.out.print("주문하고자 하는 제품의 넘버를 입력해주세요 : ");
                int shoesOptNum = scanner.nextInt();
                //상품 주문하기 페이지로 이동
                break;
            } else if (result.equals("N")) {
                break;
            } else {
                System.out.println("메뉴를 잘못 입력하셨습니다.");
            }
        }

        //주문하기 N 입력시
        while (true) {
            System.out.println("------------------------------");
            System.out.println("[메뉴]");
            System.out.println("1. 이전 페이지로 돌아가기");
            System.out.println("2. 마이페이지");
            System.out.println("3. 내 주문 보기");
            System.out.println("4. 메인 페이지로 이동");
            System.out.println("5. 상품 검색 페이지");
            System.out.println("------------------------------");

            System.out.print("메뉴 번호 입력 -> ");
            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    ShowNewProducts.showNewProducts();
                    break;
                case 2:
                    UserProfile.showUserProfile();
                    break;
                case 3:
                    UserProfile.showOrders(MainPage.loggedInUserId);
                    break;
                case 4:
                    MainPage.main();
                    break;
                case 5:
                    SearchProducts.searchProducts();
                    break;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }


    }
}
