package com.example.proyectodaw2324f.product;

import com.example.proyectodaw2324f.dto.ProductDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /**
     * Método que genera productos al iniciar la app
     * @throws Exception excepción lanzada por image.getBytes()
     */
    public void generateProducts() throws Exception{
        Product product1 = new Product();
        String rutaImagen1 = "src/main/resources/static/img/cinturon.png";
        Path path1 = Paths.get(rutaImagen1);
        byte[] bytes1 = Files.readAllBytes(path1);
        if(productRepository.findProductByName("Cinturón lumbar")==null){
            product1.setName("Cinturón lumbar");
            product1.setDescription("El cinturón lumbar de TRENdGYM es un accesorio indispensable para cualquier entusiasta del fitness, especialmente diseñado para el ambiente dinámico de un gimnasio online. Este cinturón combina funcionalidad superior con un diseño ergonómico para brindar soporte y protección óptimos durante tus sesiones de entrenamiento intensas.");
            product1.setPrice(25);
            product1.setStock(100);
            product1.setImg(bytes1);
            productRepository.save(product1);
        }else{
            System.out.println("Producto 1 - Cinturón lumbar ya creado");
        }

        Product product2 = new Product();
        String rutaImagen2 = "src/main/resources/static/img/rodilleras.png";
        Path path2 = Paths.get(rutaImagen2);
        byte[] bytes2 = Files.readAllBytes(path2);
        if(productRepository.findProductByName("Rodilleras powerlifting")==null){
            product2.setName("Rodilleras powerlifting");
            product2.setDescription("Las rodilleras TRENdGYM han sido cuidadosamente diseñadas para los atletas que buscan mejorar su rendimiento en sentadillas, ofreciendo una combinación inigualable de soporte, comodidad y durabilidad. Perfectas para el gimnasio online, estas rodilleras son el complemento ideal para tu entrenamiento en casa, proporcionando la protección que necesitas para superar tus límites de manera segura.");
            product2.setPrice(25);
            product2.setStock(100);
            product2.setImg(bytes2);
            productRepository.save(product2);
        }else{
            System.out.println("Producto 2 - Rodilleras powerlifting ya creado");
        }
        Product product3 = new Product();
        String rutaImagen3 = "src/main/resources/static/img/straps.png";
        Path path3 = Paths.get(rutaImagen3);
        byte[] bytes3 = Files.readAllBytes(path3);
        if(productRepository.findProductByName("Straps TRENdGYM")==null){
            product3.setName("Straps TRENdGYM");
            product3.setDescription("Los straps de gimnasio TRENdGYM son el accesorio esencial para cualquier entusiasta del fitness que busca mejorar su agarre y rendimiento en una variedad de ejercicios. Diseñados pensando en la eficacia y la comodidad, estos straps son perfectos para el ambiente flexible y autodirigido de un gimnasio online, ofreciéndote el soporte necesario para llevar tu entrenamiento al siguiente nivel desde la comodidad de tu hogar.");
            product3.setPrice(25);
            product3.setStock(100);
            product3.setImg(bytes3);
            productRepository.save(product3);
        }else{
            System.out.println("Producto 3 - Straps TRENdGYM ya creado");
        }

    }

    /**
     * Método para registrar producto
     * @param productDTO objeto del producto
     * @throws Exception lanzada por image.getBytes()
     */
    public void registerProduct(ProductDTO productDTO) throws Exception {
        Product product = new Product();

        if(productRepository.findProductByName(productDTO.getName())!=null){
            throw new IllegalArgumentException("Este producto ya existe");
        }

        if(StringUtils.isBlank(productDTO.getName())){
            throw new IllegalArgumentException("El nombre del producto no puede estar en blanco");
        }

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setPrice(productDTO.getPrice());
        product.setImg(productDTO.getImg().getBytes());
        productRepository.save(product);

    }

    /**
     * Método que muestra una lista de los productos
     * @return una lista de productos
     */
    public List<Product> showProducts(){
       List<Product> products = productRepository.findAll();
        products.forEach(producto -> {
            if (producto.getImg() != null) {
                String imgBase64 = Base64.getEncoder().encodeToString(producto.getImg());
                producto.setImgBase64("data:image/jpeg;base64," + imgBase64);
            }
        });
       return products;
    }

    /**
     * Obtener un producto por su id
     * @param id id del producto
     * @return
     */
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    /**
     * Eliminar el producto por id
     * @param id id del producto
     */
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

}
