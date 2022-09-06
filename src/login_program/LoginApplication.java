package login_program;

import java.util.Scanner;


public class LoginApplication {
    private static boolean loginTF;

    private static int count = 1;
    private static User[] userArray = new User[10];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;

        while (run) {
            System.out.println("-----------------------------------------");
            System.out.println("1.회원가입 | 2.로그인 | 3.사용자조회 | 4.탈퇴 ");
            System.out.println("-----------------------------------------");
            System.out.print("선택 > ");
            int selcetNo = scanner.nextInt();

            if (selcetNo == 1) {
                signUp();
            } else if (selcetNo == 2) {
                logIn();
            } else if (selcetNo == 3) {
                searchUser();
            } else if (selcetNo == 4) {
                withDraw();
            }

        }

    }

    private static void withDraw() {
        System.out.println("--------");
        System.out.println("탈퇴");
        System.out.println("--------");
        System.out.print("아이디:");
        String id = scanner.next();
        System.out.print("비밀번호:");
        String password = scanner.next();
        if (loginTF) {
            for (int i = 0; i < userArray.length; i++) {
                if (userArray[i] != null) {
                    if (userArray[i].getId().equals(id) && userArray[i].getPassword().equals(password)) {
                        System.out.println("탈퇴 성공했습니다.");
                        userArray[i] = null;
                        count--;
                        loginTF = false;
                        break;
                    }
                } else {
                    System.out.println("탈퇴 실패했습니다.");
                }
            }
        } else {
            System.out.println("로그인을 먼저 해주세요");
        }
    }

    private static void searchUser() {
        System.out.println("-------");
        System.out.println("회원목록");
        System.out.println("-------");

        if (loginTF) {
            for (User user : userArray) {
                if (user != null) {
                    System.out.println(user.getName());
                }
            }
        } else {
            System.out.println("로그인을 먼저 해주세요");
        }
    }

    private static void logIn() {
        System.out.println("--------");
        System.out.println("로그인");
        System.out.println("--------");
        System.out.print("아이디:");
        String id = scanner.next();
        System.out.print("비밀번호:");
        String password = scanner.next();

        User user = findIdPd(id, password);

        if (user != null) {
            System.out.println("로그인에 성공했습니다.");
            System.out.println("사용자아이디 :" + user.getId());
            System.out.println("사용자 닉네임 :" + user.getName());
            loginTF = true;
        } else {
            System.out.println("로그인에 실패하였습니다.");
        }
    }

    private static User findIdPd(String id, String password) {
        User user = null;
        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i] != null) {
                if (userArray[i].getId().equals((id)) && userArray[i].getPassword().equals(password)) {
                    user = userArray[i];
                    break;
                }
            }
        }
        return user;
    }


    private static void signUp() {
        System.out.println("--------");
        System.out.println("회원가입");
        System.out.println("--------");
        System.out.print("아이디:");
        String id = scanner.next();
        System.out.print("비밀번호:");
        String password = scanner.next();
        System.out.print("닉네임:");
        String name = scanner.next();


        for (int i = 0; i < userArray.length; i++) {

            if (userArray[i] == null) {
                for (int j = 0; j < userArray.length; j++) {

                    if (userArray[j] == null) {
                        User user = new User(id, password, name);
                        userArray[i] = user;
                        count++;
                        System.out.println("회원가입 되었습니다.");
                        break;
                    }
                    else if (userArray[j].getId().equals(id)) {
                        System.out.println("중복된 아이디입니다.");
                        break;
                    }
                    else if (userArray[j].getName().equals(name)) {
                        System.out.println("중복된 닉네임입니다.");
                        break;
                    }
                 else if (count > userArray.length) {
                        System.out.println("회원 가입을 할수 없습니다.");
                        break;
                    }
                }
                break;

            }


        }

    }
}
