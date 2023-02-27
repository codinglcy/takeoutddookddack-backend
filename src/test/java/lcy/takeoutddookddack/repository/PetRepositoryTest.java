package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.PetEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
public class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @BeforeEach
    public void before(){
        petRepository.removeAll();
    }

    @Test
    public void createTest(){
        PetEntity pet1 = PetEntity.builder().kind("CAT").name("나비").age(3).build();
        petRepository.newPet(pet1);

        PetEntity findPet = petRepository.findById(pet1.getId());

        System.out.println("findPet = " + findPet.getId() + findPet.getKind() + findPet.getName() + findPet.getAge() +" "+ findPet.getCreatedAt());
        Assertions.assertThat(pet1.getId()).isEqualTo(findPet.getId());
        Assertions.assertThat(pet1.getName()).isEqualTo(findPet.getName());
        Assertions.assertThat(pet1.getKind()).isEqualTo(findPet.getKind());
        Assertions.assertThat(pet1.getAge()).isEqualTo(findPet.getAge());
    }

    @Test
    @DisplayName("pet2저장, name변경")
    public void updateNameTest(){
        PetEntity pet2 = PetEntity.builder().kind("DOG").name("뭉게").age(4).build();
        petRepository.newPet(pet2);

        PetEntity findPet2 = petRepository.findById(pet2.getId());
        System.out.println("findPet2 = " + findPet2.getId()+findPet2.getKind()+findPet2.getName()+findPet2.getAge()+" "+findPet2.getCreatedAt()+" "+findPet2.getUpdatedAt());

        petRepository.updateWithId(pet2.getId(),"DOG","까미",3);

        PetEntity findPet22 = petRepository.findById(pet2.getId());
        System.out.println("findPet22 = " + findPet22.getId()+findPet22.getKind()+findPet22.getName()+findPet22.getAge()+" "+findPet22.getCreatedAt()+" "+findPet22.getUpdatedAt());

    }
}
