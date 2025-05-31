package api.endpoints;

public class PetClinic {

    private static final PetClinic instance = new PetClinic();
    public OwnerService ownerService = new OwnerService();
    public PetService petService = new PetService();

    private PetClinic() {
    }

    public static PetClinic getInstance() {
        return instance;
    }
}
