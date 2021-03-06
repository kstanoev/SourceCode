package com.telerikacademy.cooking;

import com.telerikacademy.Global;
import com.telerikacademy.exceptions.user.UserAccessDeniedException;
import com.telerikacademy.interfaces.Component;
import com.telerikacademy.messages.Comment;
import com.telerikacademy.users.Admin;
import com.telerikacademy.users.Author;
import com.telerikacademy.users.User;

import java.awt.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;


public class Recipe {
    private int id;
    private String title;
    private User author;
    private String description;
    private Image image;
    private Queue<Step> steps;
    private Map<String, Component> recipe = new HashMap<>();
    private List<Comment> comments;
    private Timestamp recipeAdded;

    public Recipe(String title, String description) {
        if (Global.currentUser instanceof Author || Global.currentUser instanceof Admin) {
            this.setId();
            this.title = title;
            this.author = Global.currentUser;
            this.description = description;
            steps = new LinkedList<>();
            recipe = new HashMap<>();
            comments = new ArrayList<>();
            this.recipeAdded = new Timestamp(System.currentTimeMillis());
        } else {
            throw new UserAccessDeniedException("Current user has no permission to create recipes");
        }
    }


    public int getId() {
        return id;
    }

    public void setId() {
        this.id = Global.RECIPE_ID++;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public Map<String, Component> getRecipe() {
        return recipe;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public Queue<Step> getSteps() {
        return steps;
    }

    public Timestamp getRecipeAdded() {
        return recipeAdded;
    }

    public void addIngredient(String amount, Component ingredient) {
        this.recipe.put( amount, ingredient );
    }


    public void addStep(Step step) {
        this.steps.add( step );
    }

    public void addComment(Comment comment) {
        this.comments.add( comment );
    }


    @Override
    public String toString() {
        return String.format( "Recipe %s. %s, Author -> %s%n%s ", this.getId(), this.getTitle(), this.getAuthor(),
                this.getDescription() );
    }

}

