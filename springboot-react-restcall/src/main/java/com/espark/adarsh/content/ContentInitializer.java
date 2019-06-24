package com.espark.adarsh.content;

import com.espark.adarsh.entity.Category;

@org.springframework.stereotype.Component
public class ContentInitializer {

    @org.springframework.beans.factory.annotation.Autowired
    private com.espark.adarsh.repostiory.CategoryRepository categoryRepository;

    @javax.annotation.PostConstruct
    @javax.transaction.Transactional
    public void init(){
        /*
            -- Category --

            Department
              |--Home
              |    |--Furniture
              |    |    |-Living Room
              |    |    |   |-Sofa
              |    |    |   |-Almira
              |    |    |
              |    |    |-Bed Room
              |    |    |   |-Bed
              |    |    |   |-Matres
              |    |    |
              |    |    |-Kitchen
              |    |    |   |-Containers
              |    |    |   |-Crockery
              |    |    |
              |    |-Electronic
              |         |-TV
              |         |-Fridge
              |         |-Washing Machine
              |
              |-- Mens
              |     |-Shirt
              |     |   |-FullShirt
              |     |   |-HalfShirt
              |     |
              |     |-Pant
              |     |   |-FullPant
              |     |   |-HalfPant
              |     |-Watch
              |     |   |-AnalogWatch
              |     |   |-DigitalWatch
              |     |
              |-- Womens
              |     |-Shirt
              |     |   |-FullShirt
              |     |   |-HalfShirt
              |     |
              |     |-Pant
              |     |   |-FullPant
              |     |   |-HalfPant
              |     |-Watch
              |     |   |-AnalogWatch
              |     |   |-DigitalWatch
              |
              |-- Kid
              |     |-Shirt
              |     |   |-FullShirt
              |     |   |-HalfShirt
              |     |
              |     |-Pant
              |     |   |-FullPant
              |     |   |-HalfPant
              |     |
              |     |-Watch
              |     |   |-AnalogWatch
              |     |   |-DigitalWatch
        */

        Category department=new Category(com.espark.adarsh.util.ApplicationUtil.HOME,null);
        department.setCatId(1L);
        department=saveOrUpdate(department);

        Category home=new Category("home",department);
        home=saveOrUpdate(home);

        Category furniture=new Category("furniture",home);
        furniture=saveOrUpdate(furniture);

        Category livingRoom=new Category("living-room",furniture);
        livingRoom=saveOrUpdate(livingRoom);

        Category sofa=new Category("sofa",livingRoom);
        sofa=saveOrUpdate(sofa);

        Category almira=new Category("almira",livingRoom);
        almira=saveOrUpdate(almira);


        Category bedRoom=new Category("bed-room",furniture);
        bedRoom=saveOrUpdate(bedRoom);

        Category bedFrame=new Category("bed-frame",bedRoom);
        bedFrame=saveOrUpdate(bedFrame);

        Category matress=new Category("matress",bedRoom);
        matress=saveOrUpdate(matress);

        Category kitchen=new Category("kitchen",furniture);
        kitchen=saveOrUpdate(kitchen);



        Category electronic=new Category("electronic",home);
        electronic=saveOrUpdate(electronic);


        Category men=new Category("men",department);
        men=saveOrUpdate(men);

        Category menShirt=new Category("mens-shirt",men);
        menShirt=saveOrUpdate(menShirt);
        Category menFullShirt=new Category("mens-full-shirt",menShirt);
        menFullShirt=saveOrUpdate(menFullShirt);
        Category menHalfShirt=new Category("mens-half-shirt",menShirt);
        menHalfShirt=saveOrUpdate(menHalfShirt);

        Category menPant=new Category("mens-pant",men);
        menPant=saveOrUpdate(menPant);
        Category menFullPant=new Category("mens-full-pant",menPant);
        menFullPant=saveOrUpdate(menFullPant);
        Category menHalfPant=new Category("mens-half-pant",menPant);
        menHalfPant=saveOrUpdate(menHalfPant);

        Category menWatch=new Category("mens-watch",men);
        menWatch=saveOrUpdate(menWatch);
        Category menDigitalWatch=new Category("mens-digital-watch",menWatch);
        menDigitalWatch=saveOrUpdate(menDigitalWatch);
        Category menAnalogWatch=new Category("mens-analog-watch",menWatch);
        menAnalogWatch=saveOrUpdate(menAnalogWatch);

        Category women=new Category("women",department);
        women=saveOrUpdate(women);

        Category womenShirt=new Category("womens-shirt",women);
        womenShirt=saveOrUpdate(womenShirt);
        Category womenFullShirt=new Category("womens-full-shirt",womenShirt);
        womenFullShirt=saveOrUpdate(womenFullShirt);
        Category womenHalfShirt=new Category("womens-half-shirt",womenShirt);
        womenHalfShirt=saveOrUpdate(womenHalfShirt);

        Category womenPant=new Category("womens-pant",women);
        womenPant=saveOrUpdate(womenPant);
        Category womenFullPant=new Category("womens-full-pant",womenPant);
        womenFullPant=saveOrUpdate(womenFullPant);
        Category womenHalfPant=new Category("womens-half-pant",womenPant);
        womenHalfPant=saveOrUpdate(womenHalfPant);

        Category womenWatch=new Category("womens-watch",women);
        womenWatch=saveOrUpdate(womenWatch);
        Category womenDigitalWatch=new Category("womens-digital-watch",womenWatch);
        womenDigitalWatch=saveOrUpdate(womenDigitalWatch);
        Category womenAnalogWatch=new Category("womens-analog-watch",womenWatch);
        womenAnalogWatch=saveOrUpdate(womenAnalogWatch);

        Category kid=new Category("kid",department);
        kid=saveOrUpdate(kid);

        Category kidShirt=new Category("kid-shirt",kid);
        kidShirt=saveOrUpdate(kidShirt);
        Category kidFullShirt=new Category("kid-full-shirt",kidShirt);
        kidFullShirt=saveOrUpdate(kidFullShirt);
        Category kidHalfShirt=new Category("kid-half-shirt",kidShirt);
        kidHalfShirt=saveOrUpdate(kidHalfShirt);

        Category kidPant=new Category("kid-pant",kid);
        kidPant=saveOrUpdate(kidPant);
        Category kidFullPant=new Category("kid-full-pant",kidPant);
        kidFullPant=saveOrUpdate(kidFullPant);
        Category kidHalfPant=new Category("kid-half-pant",kidPant);
        kidHalfPant=saveOrUpdate(kidHalfPant);

        Category kidWatch=new Category("kid-watch",kid);
        kidWatch=saveOrUpdate(kidWatch);
        Category kidDigitalWatch=new Category("kid-digital-watch",kidWatch);
        kidDigitalWatch=saveOrUpdate(kidDigitalWatch);
        Category kidAnalogWatch=new Category("kid-analog-watch",kidWatch);
        kidAnalogWatch=saveOrUpdate(kidAnalogWatch);


        Category office=new Category("office",department);
        office=saveOrUpdate(office);

        Category stationary=new Category("stationary",office);
        stationary=saveOrUpdate(stationary);

        Category books=new Category("books",stationary);
        books=saveOrUpdate(books);

        Category pen=new Category("pen",stationary);
        pen=saveOrUpdate(pen);

        Category pencil=new Category("pencil",stationary);
        pencil=saveOrUpdate(pencil);

    }

    private Category saveOrUpdate(Category category){
        Category currentCategory = this.categoryRepository.findByCatName(category.getCatName());
        if(currentCategory==null){
            currentCategory=this.categoryRepository.save(category);
        }
        return currentCategory;
    }
}
