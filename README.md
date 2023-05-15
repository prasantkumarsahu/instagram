# Instagram
This is a Instagram backend project for `User` to signup, signin, and update details with `Authentication`. User can create `Post`, and also can view them.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot "Spring Boot") ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white "Java") ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white "Postman") ![Google Chrome](https://img.shields.io/badge/Google%20Chrome-4285F4?style=for-the-badge&logo=GoogleChrome&logoColor=white "Google Chrome")

## Frameworks and Languages
![Java v17](https://img.shields.io/badge/Java-v17-green "Java 17") ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v3.0.6-brightgreen "Spring Boot v3.0.6")
---

## Browser / Tools

## ![Google Chrome](https://img.shields.io/badge/Google%20Chrome-v112.0.5615.138-yellow "Google Chrome") ![Postman](https://img.shields.io/badge/Postman-v10.13.0-orange "Postman")

## Models

- ### User Properties
  - ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```
  - ```java
    private String firstName;
    ```
  - ```java
    private String lastName;
    ```
  - ```java
    private Integer age;
    ```
  - ```java
    private String email;
    ```
  - ```java
    private String password;
    ```
  - ```java
    private String phoneNumber;
    ```
- ### Post Properties
  - ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    ```
  - ```java
    private Timestamp createdDate;
    ```
  - ```java
    private Timestamp updatedDate;
    ```
  - ```java
    private String postData;
    ```
  - ```java
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    ```
- ### UserAuthenticationToken Properties
  - ```java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ```
  - ```java
    private String token;
    ```
  - ```java
    private LocalDate tokenCreationDate;
    ```
  - ```java
    @OneToOne
    private User user;
    ```
- ### Data To Object Classes (DTOs)
  - `UserSignUpInput`
  - `UserSignInInput`
  - `UserSignUpInOutput`
  - `UserUpdateInput`
  - `PostInput`
  - `PostOutput`
---
## Dataflow
- ### End Points / Controllers
  - _User_
    - `@PostMapping("user/signup/")`
    - `@PostMapping("user/signin/")`
    - `@PutMapping("user/update/")`
  - _Post_
    - `@PostMapping("post/")`
    - `@GetMapping("post/")`
- ### Services
  - _User_
    ```java
    public UserSignUpInOutput checkAndRegister(UserSignUpInput userSignUpInput)
    ```
    ```java
    private String encryptPassword(String password) throws NoSuchAlgorithmException
    ```
    ```java
    public boolean isAuthenticate(String email)
    ```
    ```java
    public UserSignUpInOutput loginUser(UserSignInInput userSignInInput)
    ```
    ```java
    public User getUser(Long id)
    ```
    ```java
    public void updateUser(Long id, UserUpdateInput updateInput)
    ```
  - _Post_
    ```java
    public void createPost(Long id, PostInput postInput)
    ```
    ```java
    public List<PostOutput> getAllPosts(Long id)
    ```
  - _UserAuthenticationToken_
    ```java
    public void saveToken(UserAuthenticationToken token)
    ```
    ```java
    public UserAuthenticationToken getAuthTokenByUser(User user)
    ```
- ### Repositories
  - _User_
    ```java
    public interface IUserRepository extends ListCrudRepository<User, Long> {

      boolean existsByEmail(String email);

      User findByEmail(String email);

    }
    ```
  - _Post_
    ```java
    public interface IPostRepository extends ListCrudRepository<Post, Integer> {
      List<Post> findByUser(User savedUser);
    }
    ```
  - _UserAuthenticationToken_
    ```java
    public interface IUserAuthenticationTokenRepository extends ListCrudRepository<UserAuthenticationToken, Long> {
      UserAuthenticationToken findByUser(User user);
    }
    ```
- ### Database
  In this project for datasource I've used **`MySQL` Database** for persistant memory with `SpringDataJPA`.

---

## Datastructures

- `List<>`

---

## Summary

This API is a `Spring Boot` project that is about building a robust backend for my **Instagram** clone. Where user can signup, signin, and update their details. Also user can create posts, and view all their pots all these operations requeired authentication which improves security, and password is also saved using encryption algorithm. In this project request is sent from the client on HTTP in JSON format or from path variable with server side validations and verified with authentication stored in object then response is sent back from the server by JSON format to the client.
