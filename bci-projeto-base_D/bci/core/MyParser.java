package bci.core;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


import bci.core.exception.UnrecognizedEntryException;
// MAYBE more import

class MyParser {
  private Library _library;

  MyParser(Library lib) {
    _library = lib;
  }

  void parseFile(String filename) throws IOException, UnrecognizedEntryException {
    String line;
    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
      int lineno = 0;
      while ((line = in.readLine()) != null) {
        lineno++;
        if (line.isBlank()) continue;
        try {
          parseLine(line);
        } catch (UnrecognizedEntryException e) {
          throw new UnrecognizedEntryException(e.getMessage(), e);
        }
      }
    }
  }

  private void parseLine(String line) throws UnrecognizedEntryException {
    String[] components = line.split(":");

    switch (components[0]) {
      case "USER":
        parseUser(components, line);
        break;

      case "DVD":
        parseDvd(components, line);
        break;

      case "BOOK":
        parseBook(components, line);
        break;

      default:
        throw new UnrecognizedEntryException("Tipo inválido " + components[0] + " na linha " + line);
    }
  }

  private void parseUser(String[] components, String line) throws UnrecognizedEntryException {
    try {
      if (components.length != 3)
        throw new UnrecognizedEntryException ("Número inválido de campos (3) na descrição de um utente: " + line);

      _library.registerUser(components[1], components[2]);
    } catch (UnrecognizedEntryException e) {}
  }

  private void parseDvd(String[] components, String line) throws UnrecognizedEntryException {
    if (components.length != 7)
      throw new UnrecognizedEntryException ("Número inválido de campos (7) na descrição de um DVD: " + line);
      
    String title = components[1].trim();
    Creator creator = _library.registerCreator(components[2].trim()); 
    int preco = Integer.parseInt(components[3].trim()); 
    Category category = Category.valueOf(components[4].trim()); 
    int igac = Integer.parseInt(components[5].trim());
    int nCopies = Integer.parseInt(components[6].trim()); 

    List<Creator> diretor = Collections.singletonList(creator);
    
    _library.registerDVD(title, nCopies, category, preco, igac, diretor);
  }
  
  private void parseBook(String[] components, String line) throws UnrecognizedEntryException {
    if (components.length != 7)
      throw new UnrecognizedEntryException ("Número inválido de campos (7) na descrição de um Book: " + line);
    
    String title = components[1].trim();
    int preco = Integer.parseInt(components[3].trim());
    Category category = Category.valueOf(components[4].trim());
    String isbn = components[5].trim(); 
    int nCopies = Integer.parseInt(components[6].trim());
    
    List<Creator> creators = new ArrayList<>();

    for (String name : components[2].split(","))
      creators.add(_library.registerCreator(name.trim()));

    _library.registerBook(title, nCopies, category, preco, isbn, creators);
  }
}