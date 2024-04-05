package pl.pacinho.pacman.model;

public record Position(int x, int y) {

    @Override
    public String toString() {
        return x + "," + y;
    }


}
