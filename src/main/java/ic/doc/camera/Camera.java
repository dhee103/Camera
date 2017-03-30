package ic.doc.camera;

public class Camera implements WriteListener{

    private final Sensor sensor;
    private MemoryCard memoryCard;
    private boolean powerIsOn = false;
    private boolean writing = false;

    public Camera(Sensor sensor, MemoryCard memoryCard) {
        this.sensor = sensor;
        this.memoryCard = memoryCard;
    }

    public void pressShutter() {
        if(powerIsOn) {
            memoryCard.write(sensor.readData());
            writing = true;
        }
    }

    public void powerOn() {
        sensor.powerUp();
        powerIsOn = true;
    }

    public void powerOff() {
        if(!writing) {
            sensor.powerDown();
        }
        powerIsOn = false;
    }

    @Override
    public void writeComplete() {
        writing = false;
        if(!powerIsOn) {
            powerOff();
        }
    }
}

