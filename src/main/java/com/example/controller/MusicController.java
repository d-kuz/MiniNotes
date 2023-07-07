package com.example.controller;

import com.example.model.File;
import com.example.model.Note;
import com.example.model.User;

import com.example.repository.FileRepository;
import com.example.repository.UserRepository;
import com.example.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MusicController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    NoteRepository noteRepository;
    long nUser = 0;
    long userId;
    long nFile = 0;
    long nNote = 0;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/user/login")
    public String getlogin(Model model) {
        return "login";
    }

    @GetMapping("/user/logout")
    public String getlogout(Model model) {
        userId = -1;
        return "logout";
    }
    @PostMapping("/user/login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password, Model model) {
        boolean nameUser = false;
        boolean passwordUser = false;
        for (User user: userRepository.findAll()) {
            if (user.getName().equals(name)){
                nameUser = true;
                if (user.getPassword().equals(password)){
                    passwordUser = true;
                    userId = user.getId();
                }
            }
        }
        if (nameUser){
            if (passwordUser){
                return "success";
            }else {
                return "passwordError";
            }
        }else{
            return "nameError";
        }
    }
    @GetMapping("/user")
    public String getUser(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }
    @GetMapping("/user/add")
    public String crUser(){
        return "userAdd";
    }
    @PostMapping("/user/add")
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("password1") String password1,
                             @RequestParam("password2") String password2, Model model){
        User user = new User(name, password1);
        user.setId( nUser);
        nUser++;
        model.addAttribute("user", user);
        model.addAttribute("password", password1);
        System.out.println(user.getId() + "   " + user.getName());
        if(password1.equals(password2)){
            userRepository.save(user);
            return "userAdd";
        }else{
            return "userAddError";
        }
    }

    @GetMapping("/user/file")
    public String getFile(Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        model.addAttribute("files", userOptional.get().getFiles());
        return "file";
    }

    @GetMapping("/user/file/add")
    public String crFile() {
        return "fileAdd";
    }
    @PostMapping("/user/file/add")
    public String addFile(@RequestParam("name") String name, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        System.out.println(name);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            File file = new File(name);
            file.setId(nFile);
            nFile++;
            user.addFile(file);
            userRepository.save(user);
        }
        return "fileAdd";
    }

    @GetMapping("/user/file/{idFile}/note")
    public String getNote(@PathVariable long idFile, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        System.out.println(userOptional.get().getName());
        Optional<File> fileOptional = fileRepository.findById(idFile);
        System.out.println(fileOptional.get().getName());
        model.addAttribute("notes", fileOptional.get().getNotes());
        model.addAttribute("idFile", idFile);
        return "note";
    }

    @GetMapping("/user/file/{idFile}/note/add")
    public String crNote(@PathVariable long idFile) {
        return "noteAdd";
    }
    @PostMapping("/user/file/{idFile}/note/add")
    public String addNote(@PathVariable long idFile,
                           @RequestParam("name") String name,
                           @RequestParam("noteBody") String noteBody, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<File> fileOptional = fileRepository.findById(userId);
        System.out.println(name + " " + noteBody);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            File file = fileOptional.get();
            Note note = new Note(name, noteBody);
            note.setId(nNote);
            nNote++;
            file.addNote(note);
            user.addFile(file);
            fileRepository.save(file);
            userRepository.save(user);
        }
        return "noteAdd";
    }

    @GetMapping("/user/file/{idFile}/note/{idNote}")
    public String chNote(@PathVariable long idFile, @PathVariable long idNote) {
        Optional<User> userOptional = userRepository.findById(userId);
        return "noteChange";
    }

    //удаление в корзину
    @GetMapping("/user/file/{idFile}/delete")
    public  String getdeleteFile(@PathVariable long idFile, Model model) {
        return "deleteFile";
    }
    @PostMapping("/user/file/{idFile}/delete")
    public String deleteFile(@PathVariable long idFile, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<File> fileOptional = fileRepository.findById(userId);
            File file = fileOptional.get();
            file.setAllDelete(true);
            file.setDelete(true);
            for (Note note:file.getNotes()){
                note.setDelete(true);
                noteRepository.save(note);
            }
            fileRepository.save(file);
        }
        return "file";
    }
    @GetMapping("/user/file/{idFile}/note/{idNote}/delete")
    public  String getdeleteNote(@PathVariable long idFile,
                                 @PathVariable long idNote,Model model) {
        Optional<Note> noteOptional = noteRepository.findById(userId);
        Optional<File> fileOptional = fileRepository.findById(idFile);
        Note note = noteOptional.get();
        File file = fileOptional.get();
        file.setDelete(true);
        note.setDelete(true);
        noteRepository.save(note);
        fileRepository.save(file);
        return "deleteNote";
    }

    //корзина
    @GetMapping("/user/bin")
    public String getBin(Model model){
        Optional<User> userOptional = userRepository.findById(userId);
        Set<File> files = userOptional.get().getFiles();
        model.addAttribute("folder", files);
        return "bin";
    }
    @GetMapping("/user/file/{idFile}/bin")
    public String getBinNote(@PathVariable long idFile, Model model){
        Optional<File> fileOptional = fileRepository.findById(idFile);
        Set<Note> notes = fileOptional.get().getNotes();
        model.addAttribute("folder", notes);
        return "binNote";
    }

    //восстановление
    @GetMapping("/user/file/{fileId}/note/{idNote}/remuve")
    public  String getremuveNote(@PathVariable long idFile,
                                 @PathVariable long idNote,Model model) {
        Optional<Note> noteOptional = noteRepository.findById(userId);
        Note note = noteOptional.get();
        note.setDelete(false);
        noteRepository.save(note);
        return "remuveNote";
    }
    @GetMapping("/user/file/{fileId}/remuve")
    public  String getremuveFile(@PathVariable long idFile, Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<File> fileOptional = fileRepository.findById(userId);
            File file = fileOptional.get();
            file.setAllDelete(false);
            for (Note note:file.getNotes()){
                note.setDelete(false);
                noteRepository.save(note);
            }
            fileRepository.save(file);
        }
        return "remuveFile";

    }


}