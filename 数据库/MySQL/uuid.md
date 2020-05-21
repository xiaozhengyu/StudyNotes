# UUID

UUID是全局唯一性标识（Universally Unique Identifier）的缩写。

---

## 官方说明（MySQL 8.0 Reference Manual）

### [`UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid)

Returns a Universal Unique Identifier (UUID) generated according to RFC 4122, “A Universally Unique IDentifier (UUID) URN Namespace” (http://www.ietf.org/rfc/rfc4122.txt).

<font color = orang>A UUID is designed as a number that is globally unique in space and time. Two calls to [`UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid) are expected to generate two different values, <u>even if these calls are performed on two separate devices not connected to each other.</u></font>

> Warning
>
> Although [`UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid) values are intended to be unique, <font color = orange>they are not necessarily unguessable or unpredictable</font>. If unpredictability is required, UUID values should be generated some other way.

[`UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid) returns a value that conforms to UUID version 1 as described in RFC 4122. <font color = orang>The value is a 128-bit number represented as a `utf8` string of five hexadecimal numbers in `aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee` format</font>:

>represented：表示 hexadecimal：十六进制
>
>上面这段话透露出一个重要的信息，UUID()函数的返回值是utf-8编码格式的字符串，且<font color = orange>字符串长度为36位</font>。

- The first three numbers are generated from the low, middle, and high parts of a timestamp. The high part also includes the UUID version number.

- The fourth number preserves temporal uniqueness in case the timestamp value loses monotonicity (for example, due to daylight saving time).

- The fifth number is an IEEE 802 node number that provides spatial uniqueness. A random number is substituted if the latter is not available (for example, because the host device has no Ethernet card, or it is unknown how to find the hardware address of an interface on the host operating system). In this case, spatial uniqueness cannot be guaranteed. Nevertheless, a collision should have *very* low probability.

  The MAC address of an interface is taken into account only on FreeBSD, Linux, and Windows. On other operating systems, MySQL uses a randomly generated 48-bit number.

```sql
mysql> SELECT UUID();
        -> '6ccd780c-baba-1026-9564-5b8c656024db'
```

<font color = orang>To convert between string and binary UUID values, use the [`UUID_TO_BIN()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-to-bin) and [`BIN_TO_UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_bin-to-uuid) functions. To check whether a string is a valid UUID value, use the [`IS_UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_is-uuid) function.</font>

This function is unsafe for statement-based replication. A warning is logged if you use this function when[`binlog_format`](https://dev.mysql.com/doc/refman/8.0/en/replication-options-binary-log.html#sysvar_binlog_format) is set to `STATEMENT`.

### [`UUID_SHORT()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-short)

Returns a “short” universal identifier as a 64-bit unsigned integer. Values returned by [`UUID_SHORT()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-short) differ from the string-format 128-bit identifiers returned by the [`UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid) function and have different uniqueness properties. The value of [`UUID_SHORT()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-short) is guaranteed to be unique if the following conditions hold:

- The [`server_id`](https://dev.mysql.com/doc/refman/8.0/en/replication-options.html#sysvar_server_id) value of the current server is between 0 and 255 and is unique among your set of master and slave servers
- You do not set back the system time for your server host between [**mysqld**](https://dev.mysql.com/doc/refman/8.0/en/mysqld.html) restarts
- You invoke [`UUID_SHORT()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-short) on average fewer than 16 million times per second between [**mysqld**](https://dev.mysql.com/doc/refman/8.0/en/mysqld.html) restarts

The [`UUID_SHORT()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-short) return value is constructed this way:

```clike
  (server_id & 255) << 56
+ (server_startup_time_in_seconds << 24)
+ incremented_variable++;
```

```sql
mysql> SELECT UUID_SHORT();
        -> 92395783831158784
```

Note

[`UUID_SHORT()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-short) does not work with statement-based replication.

- 

  [`UUID_TO_BIN(*`string_uuid`*)`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-to-bin), [`UUID_TO_BIN(*`string_uuid`*, *`swap_flag`*)`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-to-bin)

  Converts a string UUID to a binary UUID and returns the result. (The [`IS_UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_is-uuid) function description lists the permitted string UUID formats.) The return binary UUID is a [`VARBINARY(16)`](https://dev.mysql.com/doc/refman/8.0/en/binary-varbinary.html) value. If the UUID argument is `NULL`, the return value is `NULL`. If any argument is invalid, an error occurs.

  [`UUID_TO_BIN()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-to-bin) takes one or two arguments:

  - The one-argument form takes a string UUID value. The binary result is in the same order as the string argument.
  - The two-argument form takes a string UUID value and a flag value:
    - If *`swap_flag`* is 0, the two-argument form is equivalent to the one-argument form. The binary result is in the same order as the string argument.
    - If *`swap_flag`* is 1, the format of the return value differs: The time-low and time-high parts (the first and third groups of hexadecimal digits, respectively) are swapped. This moves the more rapidly varying part to the right and can improve indexing efficiency if the result is stored in an indexed column.

  Time-part swapping assumes the use of UUID version 1 values, such as are generated by the [`UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid) function. For UUID values produced by other means that do not follow version 1 format, time-part swapping provides no benefit. For details about version 1 format, see the [`UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid) function description.

  Suppose that you have the following string UUID value:

  ```sql
  mysql> SET @uuid = '6ccd780c-baba-1026-9564-5b8c656024db';
  ```

  To convert the string UUID to binary with or without time-part swapping, use [`UUID_TO_BIN()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-to-bin):

  ```sql
  mysql> SELECT HEX(UUID_TO_BIN(@uuid));
  +----------------------------------+
  | HEX(UUID_TO_BIN(@uuid))          |
  +----------------------------------+
  | 6CCD780CBABA102695645B8C656024DB |
  +----------------------------------+
  mysql> SELECT HEX(UUID_TO_BIN(@uuid, 0));
  +----------------------------------+
  | HEX(UUID_TO_BIN(@uuid, 0))       |
  +----------------------------------+
  | 6CCD780CBABA102695645B8C656024DB |
  +----------------------------------+
  mysql> SELECT HEX(UUID_TO_BIN(@uuid, 1));
  +----------------------------------+
  | HEX(UUID_TO_BIN(@uuid, 1))       |
  +----------------------------------+
  | 1026BABA6CCD780C95645B8C656024DB |
  +----------------------------------+
  ```

  To convert a binary UUID returned by [`UUID_TO_BIN()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-to-bin) to a string UUID, use [`BIN_TO_UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_bin-to-uuid). If you produce a binary UUID by calling [`UUID_TO_BIN()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_uuid-to-bin) with a second argument of 1 to swap time parts, you should also pass a second argument of 1 to [`BIN_TO_UUID()`](https://dev.mysql.com/doc/refman/8.0/en/miscellaneous-functions.html#function_bin-to-uuid) to unswap the time parts when converting the binary UUID back to a string UUID:

  ```sql
  mysql> SELECT BIN_TO_UUID(UUID_TO_BIN(@uuid));
  +--------------------------------------+
  | BIN_TO_UUID(UUID_TO_BIN(@uuid))      |
  +--------------------------------------+
  | 6ccd780c-baba-1026-9564-5b8c656024db |
  +--------------------------------------+
  mysql> SELECT BIN_TO_UUID(UUID_TO_BIN(@uuid,0),0);
  +--------------------------------------+
  | BIN_TO_UUID(UUID_TO_BIN(@uuid,0),0)  |
  +--------------------------------------+
  | 6ccd780c-baba-1026-9564-5b8c656024db |
  +--------------------------------------+
  mysql> SELECT BIN_TO_UUID(UUID_TO_BIN(@uuid,1),1);
  +--------------------------------------+
  | BIN_TO_UUID(UUID_TO_BIN(@uuid,1),1)  |
  +--------------------------------------+
  | 6ccd780c-baba-1026-9564-5b8c656024db |
  +--------------------------------------+
  ```

  If the use of time-part swapping is not the same for the conversion in both directions, the original UUID will not be recovered properly:

  ```sql
  mysql> SELECT BIN_TO_UUID(UUID_TO_BIN(@uuid,0),1);
  +--------------------------------------+
  | BIN_TO_UUID(UUID_TO_BIN(@uuid,0),1)  |
  +--------------------------------------+
  | baba1026-780c-6ccd-9564-5b8c656024db |
  +--------------------------------------+
  mysql> SELECT BIN_TO_UUID(UUID_TO_BIN(@uuid,1),0);
  +--------------------------------------+
  | BIN_TO_UUID(UUID_TO_BIN(@uuid,1),0)  |
  +--------------------------------------+
  | 1026baba-6ccd-780c-9564-5b8c656024db |
  +--------------------------------------+
  ```