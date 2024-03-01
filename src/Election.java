import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Election {
    private Candidate[] heap;
    private int pVotes;
    private int currentVotes = 0;

    public Election(LinkedList<String> candidates, int electorate) {

        if (candidates.size() < 2) {

            System.out.println("\nElection cannot be created with less than 2 candidates");
        }
        else {
            initializeCandidates(candidates);
            initializeElectorate(electorate);
        }
    }


     private void initializeCandidates(LinkedList<String> candidates) {

        heap = new Candidate[candidates.size()];

        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = new Candidate(candidates.get(i));
            heap[i] = candidate;
        }
    }

    private void initializeElectorate(int electorateMembers) {

         pVotes = electorateMembers;
    }

     public void castVote(String candidate) {

        if (currentVotes == pVotes) {
            System.out.println("\nNo more votes can be cast. The maximum has been reached");
        }
        else {
            boolean validCandidate = false;
            for (int i = 0; i < heap.length; i++) {
                if (Objects.equals(heap[i].getName().toLowerCase(), candidate.toLowerCase())) {
                    validCandidate = true;
                    heap[i].addVote(1);
                    currentVotes++;
                    break;
                }
            }
            if (!validCandidate) {
                System.out.println("\nCandidate not found. Please try again with valid candidate");
            }
            else {
                for (int i = heap.length/2; i >= 0; i--) {
                    heapify(heap, i, heap.length);
                }
            }
        }
    }

     public void castRandomVote() {

         if (currentVotes == pVotes) {
             System.out.println("\nNo more votes can be cast. The maximum has been reached");
         }
         else {

             Random generator = new Random();
             int choice = generator.nextInt(0, heap.length);

             heap[choice].addVote(1);
             currentVotes++;

             for (int i = heap.length / 2; i >= 0; i--) {
                 heapify(heap, i, heap.length);
             }
         }
    }

    public int getCurrentVotes() {

         return currentVotes;
    }

     public void rigElection(String candidate) {

        int largestvote = 0;
        Candidate winning = null;
        Candidate riggedFor = null;

        for (int i = 0; i < heap.length; i++) {
            if (Objects.equals(heap[i].getName().toLowerCase(), candidate.toLowerCase())) {
                riggedFor = heap[i];
            }
            if (heap[i].getVotes() > largestvote) {
                winning = heap[i];
                largestvote = heap[i].getVotes();
            }
        }
        if (riggedFor == null) {
            System.out.println("\nCandidate not found. Please try again with valid candidate");
        }
        else {
            System.out.println("Election rigged for " + riggedFor.getName());
            if (riggedFor.getVotes() < largestvote && winning != null) {
                int remainingVotes = pVotes - currentVotes;
                if (riggedFor.getVotes() + remainingVotes < largestvote) {
                    winning.setVotes(0);
                    riggedFor.setVotes(riggedFor.getVotes() + largestvote + remainingVotes);
                    currentVotes = pVotes;
                    for (int i = heap.length/2; i >= 0; i--) {
                        heapify(heap, i, heap.length);
                    }
                }
            }
            else {
                riggedFor.setVotes(pVotes);
            }
        }
    }

    public void getTopKCandidates(int k) {

        if (k > heap.length) {
            System.out.println("\nNumber of requested candidates exceeds actual");
        } else {

            String[] names = new String[k];

            for (int i = 0; i < k; i++) {
                names[i] = heap[0].getName();
                Candidate current = heap[0];
                heap[0] = heap[heap.length - (i + 1)];
                heap[heap.length - (i + 1)] = current;
                heapify(heap, 0, heap.length - (i + 1));
            }

            for (int i = heap.length / 2; i >= 0; i--) {
                heapify(heap, i, heap.length);
            }

            System.out.println("\nTop " + k + " candidates after " + currentVotes + " votes: " + Arrays.toString(names));
            System.out.println("Candidates with same number of votes are presented in no given order\n");
        }
    }

    public void auditElection() {

        System.out.println();

         for (int i = 0; i < heap.length; i++) {
             System.out.println(heap[0].getName() + "-" + heap[0].getVotes());
             Candidate current = heap[0];
             heap[0] = heap[heap.length - (i + 1)];
             heap[heap.length - (i + 1)] = current;
             heapify(heap, 0, heap.length - (i + 1));
         }

        for (int i = heap.length/2; i >= 0; i--) {
            heapify(heap, i, heap.length);
        }

        System.out.println();
    }

    private void heapify(Candidate[] heap, int i, int length) {
        int largest = i;
        int left = 2*i+1;
        int right = 2*i+2;

        if (left < length) {
            if (heap[left].getVotes() > heap[largest].getVotes()) {
                largest = left;
            }
        }

        if (right < length) {
            if (heap[right].getVotes() > heap[largest].getVotes()) {
                largest = right;
            }
        }

        if (largest != i) {
            Candidate temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            heapify(heap, largest, length);
        }
    }

}
