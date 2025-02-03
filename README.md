Title
Laptop RAM Management.

Preface: This is the second OOP project for the subject LAB211 at my uni. It offers me an insight into saving data as a binary file.
Furthermore, generating code numerically is also applied to index each element of the same model type.

Background
A computer shop specializes in managing laptop RAM. The shop organizes RAM modules by type (e.g., LPDDR5, DDR5, LPDDR4, DDR4...), and within each type, 
RAM modules are further categorized by bus speed
(e.g., 5600MHz, 4800MHz...). Finally, each bus speed category is sorted by brand. The shop requires a
management system to efficiently handle the inventory of RAM modules.

Program Specifications
Build a management program. With the following basic functions

1. Build Your Data Structure:
- Create data structures to manage RAM items, including attributes such as
code, type, bus, brand, quantity, and production_month_year.

2. Add an Item:
- Add a new RAM item to the collection.
- Ensure the code is unique and validate all other fields.

3. Search SubMenu:
- Search items by type, bus, brand.
- Option to return to the main menu.
  
4. Update Item Information:
- Update existing RAM item information by code.
- Validate and apply changes to the specified fields.
  
5. Delete Item:
- Mark a RAM item as inactive (active = false) upon confirmation.
- Ensure inactive items are not displayed in lists.
  
6. Show All Items:
- Display all active RAM items, sorted by type, bus, and brand.
  
7. Store Data to Files:
- Save the list of RAM items to a file and load data at program startup.
  
8. Quit Menu:
- Provide an option to safely exit the program.

Each menu choice should invoke an appropriate function to perform the selected menu item. Your
program must display the menu after each task and wait for the user to select another option until
the user chooses to quit the program.

**Besides, I want to extend my gratitude to Nguyen Nhat Thong for offering me the software structure and code generation idea. Without his support, I cannot make this far!**

========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To run the project from the command line, go to the dist folder and
type the following:

java -jar "LaptopRAMManagement.jar" 

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.
