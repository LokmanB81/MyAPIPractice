package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryPostPojo {
    private String name;

    public CountryPostPojo() {
    }

    public CountryPostPojo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CountryPostPojo{" +
                "name='" + name + '\'' +
                '}';
    }
}
