<?php 
require_once "connect.php";
$json=$_POST["json"];
$data=json_decode($json,true);
$response = array();
foreach ($data as $value) {
		$idDonHang=$value["idDonHang"];
		$user_id=$value["user_id"];
		$idSP=$value["idSP"];
		$TenSP=$value["TenSP"];
		$HinhSP=$value["HinhSP"];
		$GiaSP=$value["GiaSP"];
		$SoLuong=$value["SoLuong"];
		$sql="INSERT INTO chitietdonhang(id,idDonHang,user_id,idSP,TenSP,HinhSP,GiaSP,SoLuong) VALUES (null,'$idDonHang','$user_id','$idSP','$TenSP','$HinhSP','$GiaSP','$SoLuong')";
	    $result= mysqli_query($connect,$sql);
      }
      if ($result) {
      	echo "1";
      }else{
      	echo "0";
      }
		
 ?>
