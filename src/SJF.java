import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class SJF {
    private static class Process {
        private final int PID;
        private final int submitTime;
        private int burst;
        private int completeTime;
        private boolean dispatched;
        
        public Process(int PID, int submitTime, int burst) {
            this.PID = PID;
            this.submitTime = submitTime;
            this.burst = burst;
            this.completeTime = -1;
            this.dispatched = false;
        }
        
        public int getPID() {
            return this.PID;
        }
        
        public int getSubmissionTime() {
            return this.submitTime;
        }
        
        public int getBurst() {
            return this.burst;
        }
        
        public void reduceBurst(int q) {
            burst -= (int) Math.min(burst, q);
        }
        
        public void setCompleteTime(int time) {
            this.completeTime = time;
        }
        
        public int getCompleteTime() {
            return this.completeTime;
        }
        
        public boolean isCompleted() {
            return this.completeTime != -1;
        }
        
        public void setDispatched() {
            this.dispatched = true;
        }
        
        public boolean isDispatched() {
            return this.dispatched;
        }
    }
    
    private static boolean checkCompleted(Process[] parr) {
        for(Process p : parr) {
            if(!p.isCompleted()) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        PrintWriter out = new PrintWriter(osw);
        
        Process[] parr = new Process[5];
        parr[0] = new Process(0, 1, 3);
        parr[1] = new Process(1, 2, 1);
        parr[2] = new Process(2, 2, 5);
        parr[3] = new Process(3, 3, 4);
        parr[4] = new Process(4, 4, 2);
        
        int clock = 1;
        
        while(!checkCompleted(parr)) {
            // Detect shortest uncompleted job
            
            int minBurst = Integer.MAX_VALUE;
            Process minBurstProc = null;
            
            for(Process p : parr) {
                if(!p.isCompleted() && p.getSubmissionTime() <= clock) {
                    if(p.getBurst() < minBurst) {
                        minBurst = p.getBurst();
                        minBurstProc = p;
                    }
                }
            }
            
            if(minBurstProc != null) {
                clock += minBurstProc.getBurst();
                minBurstProc.reduceBurst(minBurstProc.getBurst());
                minBurstProc.setCompleteTime(clock);
            } else {
                clock++;
            }
        }
        
        out.println("PID\tCompletion Time");
        
        for(Process p : parr) {
            out.print(p.getPID());
            out.print("\t");
            out.print(p.getCompleteTime());
            out.println(" units");
        }
        
        out.close();
    }
}