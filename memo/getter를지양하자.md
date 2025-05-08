## getter 메서드를 지양하자

[여우 블로그](https://velog.io/@backfox/getter-%EC%93%B0%EC%A7%80-%EB%A7%90%EB%9D%BC%EA%B3%A0%EB%A7%8C-%ED%95%98%EA%B3%A0-%EA%B0%80%EB%B2%84%EB%A6%AC%EB%A9%B4-%EC%96%B4%EB%96%A1%ED%95%B4%EC%9A%94#%EB%82%B4%EB%B6%80-%EA%B0%92%EA%B9%8C%EC%A7%80-%EC%A0%84%EB%B6%80-%EB%B3%B5%EC%82%AC%ED%95%98%EA%B8%B0-%EA%B9%8A%EC%9D%80-%EB%B3%B5%EC%82%AC)
의 글을 읽고 난 후에 확실한 이해를 위해 예시를 작성해 보았습니다. <br />
velog에 작성하려고 했지만 접속이 안되는 관계로..

위 글을 읽고
> `getter` 메서드를 사용은 하되 참조값이 그대로 복사되는 것에 유의하고, 객체지향적으로 도메인을 설계하라.

정도로 이해했습니다.

위 글에서 `getter`에 대한 긴 글을 재미있게 설명해준 것 같아 해당 글에서는 개념에 대해서 설명은 하지 않고 <br />
예시를 간단하게 구현해보겠습니다.

### getter 메서드를 가지고 있는 클래스(Book) 생성
```java
package klago.nonProfit.common.popup.model.param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {

    private String title;
    private List<String> books = new ArrayList<>();

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<String> getBooks() {
//      return books;                                   // X (이렇게 반환하면 복사본과 원본이 동일한 참조값을 가지게 됨)
        return Collections.unmodifiableList(books);	// 읽기 전용(불변성을 가짐), 수정을 하게 되면 UnsupportedOperationException 발생!
    }
    
    public List<String> getModifiableBooks() {
        return new ArrayList<>(books);		        // 수정 가능한 List 반환
    }
    
    public void setBooks(List<String> books) {
        this.books = books;
    }
    
    /* --- books 리스트를 수정하기 위한 메서드 (객체지향적 도메인 설계) --- */
    public void updateBooks(int index, String title) {
    //		this.books.set(index, title);
        List<String> updated = new ArrayList<>(this.books);
        updated.set(index, title);
        
        this.books = updated;
    }
	
}
```

<br />
<br />

### 예시 로직 구현
```java
Book book = new Book();
book.setBooks(new ArrayList<>(Arrays.asList("Clean Code", "SpringBoot Example")));		// 원본
			
List<String> copyBooks = book.getBooks();

System.out.println("원본 메모리 주소: " + System.identityHashCode(book.getBooks()));
System.out.println("복사본 메모리 주소: " + System.identityHashCode(copyBooks));

// copyBooks.set(0, "Effective Java");			// 복사본 수정 UnsupportedOperationException 발생!
book.updateBooks(0, "Effective Java");			// 수정
book.updateBooks(1, "SpringBoot Starter");		// 수정

System.out.println("===================================");
System.out.println("원본 데이터 확인: " + book.getBooks());
System.out.println("복사본 데이터 확인: " + copyBooks);
```

<br />
<br />

### 결과 확인
![Image](https://github.com/user-attachments/assets/6a3313d0-6967-48ee-b5f5-b00761d9ab46)

![스크린샷 2025-05-08 오후 3.05.17.png](..%2F..%2F..%2F..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202025-05-08%20%EC%98%A4%ED%9B%84%203.05.17.png)