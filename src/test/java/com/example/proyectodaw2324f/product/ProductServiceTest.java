package com.example.proyectodaw2324f.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.proyectodaw2324f.dto.ProductDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void testRegisterProduct_ProductAlreadyExists() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Existing Product");

        // Mockear el comportamiento del repositorio cuando el producto ya existe
        when(productRepository.findProductByName(productDTO.getName())).thenReturn(new Product());

        // Act y Assert
        assertThrows(IllegalArgumentException.class, () -> productService.registerProduct(productDTO));

        // Verificar que el método del repositorio no fue llamado
        verify(productRepository, never()).save(any(Product.class));
    }

}