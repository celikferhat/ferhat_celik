package sample;

public class StopWatch {

        private long startTime = 0;
        private long stopTime = 0;
        private long elapsedtime = 0;
        private boolean running = false;
        private boolean started = false;
        public boolean isRunning(){return  running;}
        public void start() {
            this.startTime = System.currentTimeMillis();
            this.running = true;
            this.started = true;
        }

    /**
     * Stop timer
     */
    public void stop() {
            this.stopTime = System.currentTimeMillis();
            elapsedtime += stopTime - startTime;
            this.running = false;
            startTime = 0;
        }

    /**
     * Pause timer
     */
    public void pause(){
            if (started && running ) {
                stop();
            }
        }

    /**
     * Resume timer
     */
    public void resume(){
            if (started && !running) {
                start();
            }
        }

    /**
     * Reset timer
     */
    public void reset(){
                stop();
                started = false;
                elapsedtime = 0;
        }

    /**
     *
     * @return returns elapsed time
     */
    public long getElapsedTime() {
            long elapsed = 0;
            elapsed += elapsedtime;
            if (running) {
                elapsed += (System.currentTimeMillis() - startTime);
            }
            return elapsed;
        }



}
