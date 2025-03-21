@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Implements the Builder pattern for the class
@AllArgsConstructor // Generates a constructor with one parameter for each field in the class
@NoArgsConstructor // Generates a no-argument constructor
public class StrategyAwardEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /** 奖品库存总量 */
    private Integer awardCount;
    /** 奖品库存剩余 */
    private Integer awardCountSurplus;
    /** 奖品中奖概率 */
    private BigDecimal awardRate;
}
