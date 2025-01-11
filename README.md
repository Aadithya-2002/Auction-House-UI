# F20-21SF Coursework Project

> Author: 

## Getting started:
 
- [ ] Fork project in personal space
- [ ] Clone project on machine
- [ ] Create first issue on GitLab
- [ ] Edit code to address issue
- [ ] Commit by referencing issue number
- [ ] Push commits
- [ ] Edit README 

Refer to the instructions on published Canvas.

## Stage 1

* Created UML class diagram for the collectible items and list of collectibles.
# Books.java
* Added a class file to store the details of the collectibles(Books), which stores the datas such as unique_id, title, author, edition, genre, year of origin or date published and bidding price.
* Added another class to call all the attributes/feature in one class called books.
# CollectionList.java
* Created a CollectionList file to hold the list of collectibles.
* Used ArrayList package to holds the list of collectibles. Here, I have used ArrayList as it can even hold null values.
* Added methods to add book, list books and to find books by title, author and genre. Also added a method to get the counts of the book.
# Main.java
* Created a running example. 
* Added Scanner class to read user inputs.

## Stage 2

* Created 2 CSV file with 20 collectibles including missing values, Strings where numbers are expected.
* Added valid conditions and genres, using Set<String>.
# Books.java
* Added a constructor that accepts String array for CSV data.
# CollectionList.java
* Used BufferedReader, FileReader, IO.Exception, all in one package ( * ) to read line by line of the csv file.
* Flagged error messages using valid condition, genre and missing lines.
* Generated statistics to find the oldest and newest books, most expensive and least expensive books, and to calculate average and standard deviation prices.
# Main.java
* Added a path to input the CSV file using scanner class.
* Added an instance to generate statistics.

## Stage 3

* Created UML Class Diagram for *SuperClass* Collectible and added subclasses *Painting, Car, Books, Jewellery*.
# Collection.java
* Created the Superclass which consist of common features of all subclasses such as Title, UniqueId, Owner, Condition, BiddingPrice.
# YearEstimate.java
* Crested yearEstimate to calculate low and high estimates.
# Car.java
* Created Car subclass with attributes such as make, model and is serviced.
* Also added fromCSV to read CSV files.
# Painting.java
* Created Painting subclass and also added attributes such as style, height and width.
* Also added fromCSV to read from CSV files.
# Jewellery,java
* Created Jewellery subclass and added attributes such as type, material and gems.
* Also added fromCSV to read from CSV files.
# Books.java
* Changed Books as a subclass to superclass *Collectible*,
# CollectionList.java
* Changed name. Also made it as the collection list of all the subclasses.
*  Added readfromCSV, generate statistics and create collectible.

## Stage 4
> Created the UML class diagram with classes needed for GUI.
# AuctionHouseUI.Java
* Created the main class to run the user-interface.
* Features includes edit, add, sort, search. generate statistics, and more info button.
* Added 2 subclasses for the dialogs: EditCollectibleDialog.Java and AddCollectibleDialog.Java
* Checked whether all the features are working properly.
* Added a button to load CSV file and display the output.
# CollectionListTest.Java
* Created a unit testing class to calculate the collectible with higher Year estimate difference.
* It successfully passed the test.
