package com.github.dkuz.mininotes.controller;

import com.github.dkuz.mininotes.model.Note;
import com.github.dkuz.mininotes.model.User;
import com.github.dkuz.mininotes.repository.NoteRep;
import com.github.dkuz.mininotes.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class NoteController {

    @Autowired
    NoteRep noteRep;
    @Autowired
    UserRep userRep;

    @GetMapping("/user")
    public String getUser(Model model){
        model.addAttribute("users", userRep.findAll());
        return "home";
    }
    @GetMapping("/user/add")
    public String addUser(){
        return "userAdd";
    }
    @PostMapping("/user/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("passwordUser") String  passwordUser, Model model){
        User user = new User(name, passwordUser);
        model.addAttribute("user", user);
        System.out.println(user.getId() + "   " + user.getLogin());
        userRep.save(user);
        return "userAdd";
    }

    @GetMapping("/user/{idUser}/settings")
    public String getSettings(@PathVariable long idUser,  Model model) {
        Optional<User> user = userRep.findById(idUser);
        System.out.println(user.get().getLogin());
        model.addAttribute("user", user.get().getNoteSet());
        model.addAttribute("idUser", idUser);
        return "settings";
    }
    @GetMapping("/user/{idUser}/note")
    public String getNotes(@PathVariable long idUser,  Model model) {
        Optional<User> user = userRep.findById(idUser);
        System.out.println(user.get().getLogin());
        model.addAttribute("user", user.get().getNoteSet());
        model.addAttribute("idUser", idUser);
        return "notes";
    }
    @GetMapping("/user/{idUser}note/add")
    public String addNote(@PathVariable long idUser) {
        return "noteAdd";
    }
    @PostMapping("/user/{idUser}/note/add")
    public String addNote(@PathVariable long idUser,
                           @RequestParam("name") String name,
                           @RequestParam("noteText") String noteText, Model model) {
        Optional<User> userOptional = userRep.findById(idUser);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            String date = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(new Date());
            Note note = new Note(name, noteText, date);
            user.addNote(note);
            userRep.save(user);
        }
        return "noteAdd";
    }
    @GetMapping("/user/{idUser}/note/{idNote}")
    public String getNote(@PathVariable long idUser,
                          @PathVariable long idNote, Model model) {
        Optional<User> user = userRep.findById(idUser);
        System.out.println(user.get().getLogin());
        model.addAttribute("user", user.get().getNoteSet());
        model.addAttribute("idUser", idUser);
        model.addAttribute("idNote", idNote);
        return "note";
    }
}
