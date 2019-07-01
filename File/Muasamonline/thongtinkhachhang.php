<?php 
require_once "connect.php";
if (isset($_POST["user_id"])&&isset($_POST["TenKhachHang"]) && isset($_POST["SDT"])&& isset($_POST["Email"]) && isset($_POST["DiaChi"])) {
	$user_id=$_POST["user_id"];
	$TenKhachHang=$_POST["TenKhachHang"];
	$SDT=$_POST["SDT"];
	$Email=$_POST["Email"];
	$DiaChi=$_POST["DiaChi"];
	$sql="INSERT INTO donhang (id,user_id,TenKhachHang,SDT,Email,DiaChi) VALUES (null,'$user_id','$TenKhachHang','$SDT','$Email','$DiaChi')";
	if (mysqli_query($connect, $sql)) {
			$iddonhang=$connect->insert_id;
		echo $iddonhang;
	
	}else{
		echo "Thất bại";
	}
}else{
	echo "Thông tin không được để trống!";
}
 ?>
