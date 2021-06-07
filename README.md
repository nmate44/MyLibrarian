# MyLibrarian

## EN
### Introduction
This is my assignment made for the Android basics course.

The app is following a simple idea. Get a vast amount of books - with every important data about them - from a database through an API, and allow the user to simply save the ones he's reading (or want to read) into his own local list, and allow him to swipe through them any time, let it have CRUD operations, plus track his advancement in reading (if he sets the value).

### Used for development
- Android Studio w/ Gradle
- Glide (for showing placeholder imgs)
- SQLite (for local db implementation)
- Mocky + Mockaroo (for the faked API response with a mocked Book data schema)

### Important notes
The Gradle and Framework settings are set to default for the project, because for some reason it liked to crash otherwise.

### Implementation
The app itself initializes through the **MainActivity** with 4 Fragments. 2 main with RecyclerViews, that contain all the books and the user's own saved ones, and 2 others related to a singe book item, showing the details.

First the app will fill it's arrays made of **BookModel** objects through an API JSON array answer, that is handled by the **DataGetter** class. The API itself is a fake, mocked one with a set schema as response, coming from an URL generated with Mocky.

The **BookList** fragment's RecyclerView is filled with this data. It has interactive buttons wired to every item, that either navigates the user to the **BookDetails** fragment, or adds the item to the user's own list of books.

The **UserBookList** fragment's RecyclerView is filled with the user's own books. The initialization method for this list is through a locally stored tiny SQLite database that keeps the book's Id and the user's advancement value connected to the book, and it is reached through the **DBHelper** class. It also adds dynamically every item from the BookList fragment when the Add button is clicked. There are also buttons here wired to every item, allowing the user to see the **UserBookDetails** including the advancement, and also delete a book from the list.

### Conclusion
There are many known issues and bad solutions that are kind of "forced" because of the lack of knowledge I had as a beginner, but nontheless it was a really valuable lesson to develop this project.

## HU
### Bemutatás
Ez a projekt az Android alapok órára készült beadandóm.

Az app egy egyszerű ötletet követ: Legyen adott nagy mennyiségű könyv - a hozzá tartozó összes fontos adattal együtt - egy adatbázisból API-n keresztül elérve, és a felhasználó tudjon ezekhez hozzáférni, illetve elmenteni egy saját helyi listájába azt, hogy melyiket olvassa (vagy szeretné olvasni). Legyen ez a lista mindig elérhető, tudja az alap CRUD műveleteket, és legyen megtekinthető a felhasználónak hogy hol tart éppen (ha beállítja).

### A fejlesztéshez felhasználva
- Android Studio w/ Gradle
- Glide (a placeholder képekhez)
- SQLite (a helyi adatbázis implementációjához)
- Mocky + Mockaroo (a mókolt API-ról érkező válaszhoz)

### Fontos megjegyzések
A Gradle és a Framework default beállításokon van a projektnél, mert máskülönben előszeretettel crashelt.

### Implementáció
Az app a **MainActivity**-n keresztül inicializálja magát, 4 Fragmenttel. 2 fő RecyclerView-vel rendelkező listával, amik az összes könyvet, illetve a felhasználó mentett könyveit tartalmazzák, illetve 2 másik amik egy-egy adott könyvhöz kapcsolódnak és a részleteit mutatják be.

Legelőször az app feltölti az általa kezelt listákat **BookModel** objektumokkal egy API JSON array válaszból, amit a **DataGetter** osztály kezel. Az API maga tulajdonképpen egy mesterségesen generált válasz csupán, ami egy könyv adatokat generáló sémával készült és egy egyszerű Mocky URL-en keresztül jön.

A **BookList** Fragment-ben lévő RecyclerView ezzel az adathalmazzal van feltöltve. Gombok vannak dinamikusan behuzalozva minden rekordhoz, amikkel a **BookDetails** (azaz könyv részletek) Fragment-re lehet navigálni, illetve hozzáadni a felhasználó saját listájához.

A **UserBookList** Fragment-ben lévő RecyclerView a felhasználó könyveivel van feltöltve. Az inicializációs metódus ehhez a listához egy kis méretű helyileg tárolt SQLite adatbázison keresztül történik, ami a könyv azonosítóját és a hozzá tartozó felhasználói előrehaladást (hány oldalt olvasott) tárolja. Ezt a **DBHelper** osztály kezeli. Dinamikusan hozzáadja a listához azokat a könyveket is, amiket a másik Fragmenten a gombbal hozzáadunk. Szintén vannak behuzalozva rekordonként gombok, amikkel a **UserBookDetails** (azaz felhasználó könyvének részletei) Fragment-re navigálhaunk, vagy törölhetünk a mentett könyvek közül.

### Konklúzió
Sok ismert hibája és rossz megoldása van a projektnek, amik mondhatni "erőltetettek", ami főként a tapasztalatlanságomnak tudható be a keretrendszerrel. Ettől függetlenül egy nagyon értékes és tanulságos lecke volt fejleszteni ezt a projektet.
