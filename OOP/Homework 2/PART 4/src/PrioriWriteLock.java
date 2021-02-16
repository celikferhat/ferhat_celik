/**
 * There is no library for prioritize the writer threads so I implemented this class.
 * If there are any write request ,reader threads should wait for writer threads
 */
public class PrioriWriteLock {
    /**
     * reader count
     */
    private int readers  = 0;
    /**
     * writer count
     */
    private int writers  = 0;
    /**
     * write request count
     */
    private int writeRequests = 0;

    /**
     *  If there is no writer thread and write request read lock works else , thread waits for other writer threads to finish the jobs
     * @throws InterruptedException
     */
    public synchronized void ReadLock() throws InterruptedException{
        while(writers > 0 || writeRequests > 0){
            wait();
        }
        readers++;
    }

    /**
     * decrease reader count
     */
    public synchronized void ReadUnlock(){
        readers--;
        notifyAll();
    }

    /**
     * It immediately increases writerequest and any reader thread can not take lock and if there is no writer and reader thread it locks.
     * @throws InterruptedException
     */
    public synchronized void WriteLock() throws InterruptedException{
        writeRequests++;
        while(readers > 0 || writers > 0){
            wait();
        }
        writeRequests--;
        writers++;
    }

    /**
     * decrease writer count
     * @throws InterruptedException
     */
    public synchronized void WriteUnlock() throws InterruptedException{
        writers--;
        notifyAll();
    }

}
