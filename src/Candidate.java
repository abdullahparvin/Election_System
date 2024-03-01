public class Candidate {

    private int votes;
    private final String name;

    public Candidate(String name) {
        votes = 0;
        this.name = name;
    }

    public void addVote(int vote) {
        votes = votes + vote;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }


}
