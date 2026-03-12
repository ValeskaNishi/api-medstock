package com.unimed.medstock.repository;

import com.unimed.medstock.dto.ProfitDTO;
import com.unimed.medstock.dto.StockByTypeDTO;
import com.unimed.medstock.entity.StockMovement;
import com.unimed.medstock.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    boolean existsByProductId(Long productId);

    @Query("""
            SELECT new com.unimed.medstock.dto.ProfitDTO(
                p.description,
                p.type,
                SUM(m.quantity),
                SUM((m.salePrice - p.supplierPrice) * m.quantity)
            )
            FROM StockMovement m
            JOIN m.product p
            WHERE m.movementType = 'OUTFLOW'
            GROUP BY p.id, p.description, p.type
            ORDER BY p.description
            """)
    List<ProfitDTO> findProfitByProduct();

    @Query("""
            SELECT new com.unimed.medstock.dto.StockByTypeDTO(
                p.description,
                p.type,
                p.stockQuantity,
                COALESCE(SUM(CASE WHEN m.movementType = 'OUTFLOW' THEN m.quantity ELSE 0 END), 0)
            )
            FROM Product p
            LEFT JOIN StockMovement m ON m.product.id = p.id
            WHERE p.type = :type
            GROUP BY p.id, p.description, p.type, p.stockQuantity
            ORDER BY p.description
            """)
    List<StockByTypeDTO> findStockByType(@Param("type") ProductType type);
}