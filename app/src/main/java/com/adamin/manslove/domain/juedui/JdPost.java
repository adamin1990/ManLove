package com.adamin.manslove.domain.juedui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamlee on 2016/3/30.
 */
public class JdPost {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_plain")
    @Expose
    private String titlePlain;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("excerpt")
    @Expose
    private String excerpt;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("categories")
    @Expose
    private List<JdCategory> categories = new ArrayList<JdCategory>();
    @SerializedName("tags")
    @Expose
    private List<JdTag> tags = new ArrayList<JdTag>();
    @SerializedName("author")
    @Expose
    private JdAuther author;
    @SerializedName("comments")
    @Expose
    private List<Object> comments = new ArrayList<Object>();
    @SerializedName("attachments")
    @Expose
    private List<Object> attachments = new ArrayList<Object>();
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("custom_fields")
    @Expose
    private CustomFields customFields;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The titlePlain
     */
    public String getTitlePlain() {
        return titlePlain;
    }

    /**
     *
     * @param titlePlain
     * The title_plain
     */
    public void setTitlePlain(String titlePlain) {
        this.titlePlain = titlePlain;
    }

    /**
     *
     * @return
     * The content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     * The excerpt
     */
    public String getExcerpt() {
        return excerpt;
    }

    /**
     *
     * @param excerpt
     * The excerpt
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return
     * The categories
     */
    public List<JdCategory> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories
     * The categories
     */
    public void setCategories(List<JdCategory> categories) {
        this.categories = categories;
    }

    /**
     *
     * @return
     * The tags
     */
    public List<JdTag> getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     * The tags
     */
    public void setTags(List<JdTag> tags) {
        this.tags = tags;
    }

    /**
     *
     * @return
     * The author
     */
    public JdAuther getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(JdAuther author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The comments
     */
    public List<Object> getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The attachments
     */
    public List<Object> getAttachments() {
        return attachments;
    }

    /**
     *
     * @param attachments
     * The attachments
     */
    public void setAttachments(List<Object> attachments) {
        this.attachments = attachments;
    }

    /**
     *
     * @return
     * The commentCount
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     *
     * @param commentCount
     * The comment_count
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     *
     * @return
     * The commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     *
     * @param commentStatus
     * The comment_status
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     *
     * @return
     * The customFields
     */
    public CustomFields getCustomFields() {
        return customFields;
    }

    /**
     *
     * @param customFields
     * The custom_fields
     */
    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }

}
