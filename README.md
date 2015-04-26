1 通过随机数模拟电压、电流、温度、温度和RFID标签监测数据，并用TCP进行数据传输.
2 数据库建表。(ps:数据库中只存放了瞬时值，没有存放历史值)
 传感器数据表：id, 监测点id,电压，电流，温度，湿度，检测时间
create table sense_data(
  id int(10) primary key,
  addressId int(5) not null,
  voltage float(6,2) not null,
  current float(6,2) not null,
  temp    float(6,2) not null,
  dampness float(6,2) not null, 
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
RFID数据表:id,监测点id,产品编号id,加工程度ID，监测时间,
create table rfid_data(
  id int(10) primary key,
  addressId int(5) not null,
  productId int(5) not null,
  stateId   int(2) not null,
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
3 对于模拟出来的传感器数据(sense_data)：
监测点共有7个，id为1，2...7;
电压值在219-221之间,保留2位小数；
电流值在0-2之间,保留2位小数
温度值在24-32之间,保留2位小数
湿度值在0.25-0.65之间，中间值为0.45，保留2位小数
4 对于模拟出来的RFID数据（rfid_data）：
监测点共有5个，id为0，1...5；
产品编号共有21个，产品编号id为0,1...21；
加工程度等级共有7个，加工程度id为0,1。。7；
5 服务器端采用多线程，可以多个客户端连接。