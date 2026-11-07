# SmartQueueAPI

## Layihə haqqında

SmartQueueAPI müştəri növbələrinin idarə olunması üçün hazırlanmış RESTful Backend API layihəsidir.

Layihə müştərilərin növbəyə əlavə edilməsini, növbədəki mövqelərinin öyrənilməsini və növbə çatdıqda növbəti müştərinin çağırılmasını təmin edir.

Növbələrin idarə olunmasında FIFO (First In, First Out) prinsipi tətbiq olunur. Yəni növbəyə ilk daxil olan müştəri ilk çağırılır.

## İstifadə olunan texnologiyalar

- Java 
- Spring Boot 
- Spring Data JPA 
- Hibernate – ORM
- PostgreSQL 
- Lombok
- Maven
- Swagger - API Documentation

## Əsas funksionallıqlar

- Yeni müştərinin növbəyə əlavə edilməsi.
- FIN kodu vasitəsilə müştərinin növbədəki yerinin müəyyən edilməsi.
- Növbəti müştərinin çağırılması.
- Müştəri statuslarının idarə olunması.
- Müştərinin növbədən çıxarılması
- FIN kodunun təkrarlanmasının qarşısının alınması.
- FIFO prinsipi əsasında növbənin idarə olunması.

## API Endpoint-lər

- `POST` | `/add-customer-queue` | Yeni müştərini növbəyə əlavə edir. |
- `GET` | `/get-queue-customers` | Hazırda növbədə olan bütün müştəriləri qaytarır. |
- `GET` | `/get-customer-queue` | FIN koduna əsasən müştərinin növbədəki mövqeyini müəyyən edir. |
- `POST` | `/next-customer` | Növbəti müştərini çağırır və növbəni yeniləyir. |
- `DELETE` | `/delete-customer` | FIN koduna əsasən müştərini növbədən silir. |

## Verilənlər bazası (Database)

Layihədə PostgreSQL relational verilənlər bazasından istifadə olunur.

## Layihənin işə salınması

1. Repository-ni klonlayın:

   ```bash
   git clone https://github.com/Calil13/SmartQueueAPI.git
   ```

2. Layihəni IntelliJ IDEA-da açın.

3. PostgreSQL-də `smart-queue` adlı database yaradın.

4. `application.properties` faylında database məlumatlarını konfiqurasiya edin:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/smart-queue
   spring.datasource.username=admin
   spring.datasource.password=YOUR_PASSWORD

   spring.jpa.hibernate.ddl-auto=update
   ```

5. Spring Boot tətbiqini işə salın.

Tətbiq aşağıdakı port üzərindən işləyir:

`http://localhost:8084`

## /next-customer(Həlli)

- Problem - eyni anda /next-customer endpoint-i 2 nəfər tərəfindən çağırılır, bunun qarşısın necə almaq olar?

- Həll- burada 2 həll ola bilər.
1) Customer cədvəlində createdAt field mövcudur, biz /next i işə salarkən methoda belə bir məntiq 
qururuqki ArrayDequeue daxilində olan customerlərin hamısın for ilə gəzsin və növbəyə ən tez kim
əlavə olunubsa onu /next edirik
2) synchronized dan istifadə edərək
