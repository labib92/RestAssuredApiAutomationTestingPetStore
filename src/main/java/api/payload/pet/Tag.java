package api.payload.pet;

public class Tag {
    private int id;
    private String name;

    public Tag(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
