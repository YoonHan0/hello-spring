package hello.hello_spring.domain;

public class Cat extends Pet {

    public Cat(int age, String name) {
        super(age);         // 부모 추상 클래스 생성자 호출하면서 값을 넣어줌
        this.name = name;
    }

    // 상속 받은 부모(추상) 메소드를 직접 구현
    public void walk() {
        System.out.println("Cat walk!");
    }
    public void eat() {
        System.out.println("Cat eat!");
    }
    public String name;
}
