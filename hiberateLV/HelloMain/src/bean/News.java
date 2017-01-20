package bean;

import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.Transient;
import java.beans.*;
import java.util.*;

/**
 * Created by chenlian on 16/6/10.
 */

@Entity
@Table(name = "news_inf")
public class News {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="fk", strategy = "uuid")
    @GeneratedValue(generator = "fk")
    private String id;

    @Column(name = "title")
    private String title;


    @Column(name = "content")
    private String content;

    @Transient
    private String fullTime = "transient test";

    @Enumerated(EnumType.ORDINAL)
    private Season happenSesson = Season.autumn;

    @Temporal(TemporalType.TIME)
    private Date birthday = new Date();


    @Temporal(TemporalType.DATE)
    private Date birthdayY = new Date();

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name="baoshe_inf",joinColumns=@JoinColumn(name="News_id", nullable = false))
    @Column(name="baoshe_name")
    @OrderColumn(name="list_order")
    private List<String> baoshes = new ArrayList<>();


    @ElementCollection(targetClass = String.class)
    @CollectionTable(name="dianshitai_inf",joinColumns=@JoinColumn(name="News_id", nullable = false))
    @Column(name="dianshitai_dianshitai" )
    private Set<String> dianshitai = new HashSet<>();

    @ElementCollection(targetClass = Float.class)
    @CollectionTable(name="score_inf",joinColumns=@JoinColumn(name="News_id", nullable = false))
    @MapKeyColumn(name="score_score" )
    @MapKeyClass(String.class)
    @Column(name="mark")
    private Map<String, Float> scores = new HashMap<>();

    public Season getHappenSesson() {
        return happenSesson;
    }

    public void setHappenSesson(Season happenSesson) {
        this.happenSesson = happenSesson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getBaoshes() {
        return baoshes;
    }

    public void setBaoshes(List<String> baoshes) {
        this.baoshes = baoshes;
    }

    public Set<String> getDianshitai() {
        return dianshitai;
    }

    public void setDianshitai(Set<String> dianshitai) {
        this.dianshitai = dianshitai;
    }

    public Map<String, Float> getScores() {
        return scores;
    }

    public void setScores(Map<String, Float> scores) {
        this.scores = scores;
    }

    public static enum Season {
        spring,
        summer,
        autumn,
        winter
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFullTime() {
        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
    }
}
