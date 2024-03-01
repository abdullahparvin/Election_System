import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

@SuppressWarnings("DuplicatedCode")
public class ElectionSystem {

    public static void newElection(LinkedList<String> candidates, int electorate) {

        System.out.println("\nSimulating ''regular'' election");

        Election election = new Election(candidates, electorate);

        int session = 1;

        while (election.getCurrentVotes() < electorate) {

            System.out.println("voting session " + session + " :");
            session++;

            election.castVote(candidates.get(0));
            System.out.println("votes: " + election.getCurrentVotes());
            election.castVote(candidates.get(0));
            System.out.println("votes: " + election.getCurrentVotes());

            System.out.println("\nElection audit after " + election.getCurrentVotes() + " votes :");

            election.auditElection();

            System.out.println("voting session " + session + " :");
            session++;

            election.castRandomVote();
            System.out.println("votes: " + election.getCurrentVotes());
            election.castRandomVote();
            System.out.println("votes: " + election.getCurrentVotes());
            election.castRandomVote();
            System.out.println("votes: " + election.getCurrentVotes());

            election.getTopKCandidates(3);
        }

        election.auditElection();
    }

    public static void newRiggedElection(LinkedList<String> candidates, int electorate) {

        System.out.println("\nSimulating rigged election");

        Election riggedElection = new Election(candidates, electorate);
        int session = 1;

        while (riggedElection.getCurrentVotes() < electorate) {

            System.out.println("voting session " + session + " :");
            session++;

            riggedElection.castVote(candidates.get(0));
            System.out.println("votes: " + riggedElection.getCurrentVotes());
            riggedElection.castVote(candidates.get(0));
            System.out.println("votes: " + riggedElection.getCurrentVotes());

            System.out.println("\nElection audit after " + riggedElection.getCurrentVotes() + " votes :");

            riggedElection.auditElection();

            System.out.println("voting session " + session + " :");
            session++;

            riggedElection.castRandomVote();
            System.out.println("votes: " + riggedElection.getCurrentVotes());
            riggedElection.castRandomVote();
            System.out.println("votes: " + riggedElection.getCurrentVotes());
            riggedElection.castRandomVote();
            System.out.println("votes: " + riggedElection.getCurrentVotes());

            riggedElection.getTopKCandidates(3);
        }

        System.out.println("\nElection audit after rigging");

        riggedElection.rigElection(candidates.get(1));

        riggedElection.auditElection();
    }

    public static void main(String[] args) {

        String[] peoples = new String[]{"Marcus Fenix", "Dominic Santiago", "Damon Baird", "Cole Train", "Anya Stroud", "Augustus Cole"};
        LinkedList<String> people = new LinkedList<>(Arrays.asList(peoples));

        newElection(people, peoples.length);

        newRiggedElection(people, peoples.length);

    }
}
