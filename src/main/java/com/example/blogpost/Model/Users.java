package com.example.blogpost.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class Users {

    @Id
    private String _id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Indexed(unique = true)
    private String phoneNumber;

    @NotBlank(message = "Gender cannot be empty")
    private String gender;

    private String userType = "farmer";
    private String location = "Unknown";
    private int totalAnimals = 0;
    private String profilePicture = "";
    private String bio = "";
    private Date dob = new Date();

    private boolean verificationStatus = false;

    private Map<String, String> socialLinks = new HashMap<>(4) {{
        put("facebook", "");
        put("website", "");
        put("instagram", "");
        put("youtube", "");
    }};

    private Map<String, Integer> impressions = new HashMap<>(4) {{
        put("totalViews", 0);
        put("totalPosts", 0);
        put("totalLikes", 0);
        put("totalReposts", 0);
    }};

    private boolean isActive = true;

    @LastModifiedDate
    private Date lastLogin = new Date();

    @CreatedDate
    private Date createdAt = new Date();

    @LastModifiedDate
    private Date updatedAt = new Date();

    
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalAnimals() {
        return totalAnimals;
    }

    public void setTotalAnimals(int totalAnimals) {
        this.totalAnimals = totalAnimals;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Map<String, String> getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String platform,String link) {
        this.socialLinks.put(platform,link);
    }

    public Map<String, Integer> getImpressions() {
        return impressions;
    }

    public void setImpressions(String type,Integer count) {
        this.impressions.put(type, count);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}