package hello.hello_spring.domain;

abstract class Pet {

    // 추상 클래스 생성자
    public Pet(int age) {
        this.age = age;
    }
    abstract public void walk(); // 추상 메소드
    abstract public void eat(); // 추상 메소드

    public int age;      // 인스턴스 필드
    public void run() {  // 인스턴스 메소드
        System.out.println("run run");
    }
}

