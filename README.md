# 🏕️ Refugees Camp

Java EE backend for managing refugee camps, built as a Maven multi-module project for the ESPRIT PIDEV course.

**Team:** Tech No Logic

## 📋 Overview

Refugees Camp is a refugee camp management platform that exposes a JAX-RS REST API. It covers refugee records, camp operations, stock and donations, volunteer events, job offers, training courses, and news. Authentication uses JWT with role-based access control on protected endpoints.

## 🛠️ Tech Stack

| Layer       | Technology                        |
| ----------- | --------------------------------- |
| Language    | Java 8                            |
| Platform    | Java EE 7                         |
| App server  | WildFly 9.0.1                     |
| Persistence | JPA / Hibernate 4.3               |
| Database    | MySQL                             |
| REST        | JAX-RS (`/api`)                   |
| Auth        | JWT, Google OAuth, Facebook OAuth |
| Payments    | PayPal REST SDK                   |
| Build       | Maven                             |

## 📁 Project Structure

```
refugeesCamp/
├── refugeesCamp-ejb/     # Business logic, JPA entities, EJB services
├── refugeesCamp-web/     # JAX-RS resources, auth filters, WAR packaging
├── refugeesCamp-ear/     # Enterprise archive for WildFly deployment
└── refugeesCamp-client/  # Remote EJB client tests
```

### 📦 Modules

- **refugeesCamp-ejb** — Stateless session beans (`@Stateless`), domain entities, and remote/local service interfaces.
- **refugeesCamp-web** — REST endpoints under `/api`, authorization filters, and servlet utilities.
- **refugeesCamp-ear** — Bundles the EJB and WAR modules and deploys to WildFly on `mvn install`.
- **refugeesCamp-client** — Standalone tests that call EJB services over JNDI.

## ✨ Domain Features

- **Refugees** — Profiles, statistics, age categories, and medical folders
- **Camps** — Camp management and assignment
- **Stock & needs** — Inventory, need requests (accept/decline), and low-stock notifications
- **Donations** — PayPal integration and camp-level donation tracking
- **Events** — Volunteer events with ratings
- **Job offers** — Matching refugees to offers and PDF cover-letter generation
- **Courses** — Training course management
- **News** — News articles filtered by country
- **Media** — File upload and retrieval
- **Users** — Admin, DistrictChef, CampChef, and Volunteer roles

## ✅ Prerequisites

- JDK 8
- Maven 3.x
- WildFly 9.0.1
- MySQL 5.x
- MySQL JDBC driver installed in WildFly

## 🗄️ Database Setup

1. Create a MySQL database named `refugeescamp`.
2. Open the WildFly admin console at `http://127.0.0.1:19990` (default credentials: `admin` / `wildflyadmin`).
3. Go to **Configuration → Subsystems → Datasources**.
4. Add a **MySQL datasource**:
   - JDBC URL: `jdbc:mysql://localhost:3306/refugeescamp`
   - JNDI name: `java:/rcDS` (must match `persistence.xml`)
   - Set your MySQL username and password
5. Test the connection from the WildFly console.

The persistence unit is configured in `refugeesCamp-ejb/src/main/resources/META-INF/persistence.xml` with `hibernate.hbm2ddl.auto=create-drop`, so the schema is recreated on each deployment.

## 🚀 Build and Deploy

From the project root:

```bash
mvn clean install
```

The `refugeesCamp-ear` module uses the WildFly Maven plugin to deploy automatically on install (management port `19990`). The deployed application context is typically:

```
http://localhost:8080/refugeesCamp-web/api/
```

## 🔌 REST API

All resources are mounted under `/api` (see `RestActivator`).

| Resource            | Base path             | Description                   |
| ------------------- | --------------------- | ----------------------------- |
| Login               | `/home`               | Login, password reset, OAuth  |
| Public              | `/public`             | Unauthenticated endpoints     |
| Users               | `/users`              | User CRUD, search, profile    |
| Refugees            | `/Refugees`           | Refugee management and stats  |
| Camps               | `/camps`              | Camp management               |
| Stock               | `/stock`              | Inventory and need workflows  |
| Stock notifications | `/stock/notification` | Low-stock alerts              |
| Donations           | `/donations`          | Donations and PayPal flow     |
| Events              | `/evenements`         | Events and ratings            |
| Job offers          | `/joboffers`          | Offers and candidate matching |
| Courses             | `/course`             | Training courses              |
| News                | `/news`               | News articles                 |
| Media               | `/media`              | File upload/download          |
| Needs               | `/need`               | Camp need requests            |

## 🔒 Security

Protected endpoints use the `@AllowTo` annotation to restrict access by role. A JAX-RS dynamic feature (`AccesDynamicFeature`) registers a filter when `@AllowTo` is present on a resource method.

**Allowed roles:** `Admin`, `CampChef`, `DistrictChef`, `Volunteer`

**Example:**

```java
@GET
@Produces(MediaType.APPLICATION_JSON)
@AllowTo({"Admin", "DistrictChef"})
public Response doSomething() {
    // Only Admin and DistrictChef can call this method
}
```

Authentication supports:

- Credentials (email/password) with JWT bearer tokens
- Google OAuth (`GoogleAuth`)
- Facebook OAuth (`FacebookAuth`)

Send authenticated requests with an `Authorization: Bearer <token>` header.

## 🧪 Client Tests

The `refugeesCamp-client` module contains remote EJB tests. Configure JNDI in `refugeesCamp-client/src/main/resources/jndi.properties` before running tests against a deployed server.

## 🔗 Original Repository

```
git@gitlab.com:technologic-esprit/Refugees-camp-JEE.git
```
