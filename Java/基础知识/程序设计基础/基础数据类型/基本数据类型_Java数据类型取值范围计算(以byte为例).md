| 类型 | 存储需求 | 取值范围    |
| ---- | -------- | ----------- |
| byte | 1字节    | -128 ~ +127 |

取值范围是如何得到的：

1. 1字节 == 8个比特位
2. 1位存储符号（0：正数  1：负数）
3. 7位存储数值
4. Java采用补码表示数据（正数的补码与原码相同，负数的补码等于原码取反+1）

最大值：0 1111111（补码） => 0 1111111（原码）= 2<sup>7</sup>-1 = 127

最小值：1 0000000（补码）=>0 1111111（补码-1）=>1 0000000（原码）= - 2<sup>7</sup> = -128