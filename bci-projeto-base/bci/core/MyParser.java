package bci.core;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

  // Assumo que há um método em Library para registar um utente (por exemplo, com o nome registerUser
  // Caso o método lance alguma excepção do core, então será necessário apanhá-la. Se não lançar,
  // tirar o try-catch
  private void parseUser(String[] components, String line) throws UnrecognizedEntryException {
    try {
      if (components.length != 3)
        throw new UnrecognizedEntryException ("Número inválido de campos (3) na descrição de um utente: " + line);

      _library.registerUser(components[1], components[2]);
    } catch (UnrecognizedEntryException e) {}
  }

  // Assumo que há um método em Library que devolve o criador dado um nome (e cria-o caso não exista)
  // com o nome registerCriator(String)
  // Há um método que regista um DVD em Library dado os vários componentes ou um método que adiciona uma obra
  private void parseDvd(String[] components, String line) throws UnrecognizedEntryException {
    if (components.length != 7)
      throw new UnrecognizedEntryException ("Número inválido de campos (7) na descrição de um DVD: " + line);
      
    // 1. EXTRAÇÃO DOS DADOS E CONVERSÃO DE TIPOS
    String title = components[1].trim();
    Creator creator = _library.registerCreator(components[2].trim()); // Objeto Creator
    int preco = Integer.parseInt(components[3].trim()); // Preço (int)
    Category category = Category.valueOf(components[4].trim()); // Categoria (Category)
    int igac = Integer.parseInt(components[5].trim()); // IGAC (int)
    int nCopies = Integer.parseInt(components[6].trim()); // Cópias (int)

    // O método registerDVD espera uma List<Creator> para 'diretor'.
    // Usamos Collections.singletonList() para criar uma lista de tamanho 1, que é o objeto 'creator'.
    List<Creator> diretor = Collections.singletonList(creator);
    
    // 2. CHAMADA AO MÉTODO registerDVD NA ORDEM CORRETA
    // registerDVD(title, copies, category, preco, igac, diretor)
    _library.registerDVD(title, nCopies, category, preco, igac, diretor);
  }
  
  private void parseBook(String[] components, String line) throws UnrecognizedEntryException {
    if (components.length != 7)
      throw new UnrecognizedEntryException ("Número inválido de campos (7) na descrição de um Book: " + line);
    
    // 1. EXTRAÇÃO DOS DADOS E CONVERSÃO DE TIPOS
    String title = components[1].trim();
    int preco = Integer.parseInt(components[3].trim());
    Category category = Category.valueOf(components[4].trim());
    String isbn = components[5].trim(); // O ISBN não pode ser passado para o parâmetro 'igac' (int)
    int nCopies = Integer.parseInt(components[6].trim());
    
    List<Creator> creators = new ArrayList<>();
    // Assumindo que authors (criadores) estão em components[2] separados por vírgula
    for (String name : components[2].split(","))
      creators.add(_library.registerCreator(name.trim()));

    // 2. CHAMADA AO MÉTODO registerBook NA ORDEM CORRETA
    // A assinatura é: registerBook(title, copies, category, preco, igac, diretor)
    
    // *** ATENÇÃO: O seu método registerBook espera um 'int igac', mas o ficheiro de import 
    // para um livro fornece um ISBN (String) no components[5]. ***
    // Estamos a usar '0' como valor dummy para o 'igac' para que o código compile,
    // mas isto é um erro de design que deve ser corrigido alterando a assinatura de registerBook.
    int igacDummy = 0;
    
    // Chamada final: (title, copies, category, preco, igac, diretor)
    _library.registerBook(title, nCopies, category, preco, isbn, creators);
  }
}