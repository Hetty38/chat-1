
package com.nc.training.center.chat.domains;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    @JoinTable(name = "users_chats",
            joinColumns = @JoinColumn(name = "group_chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersInChat;
    private String name;

    public GroupChat() {
    }

    public List<User> getUsersInChat() {
        return usersInChat;
    }

    public void setUsersInChat(List<User> usersInChat) {
        this.usersInChat = usersInChat;
    }


    public GroupChat(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}