<?php 
require_once "connect.php";
$sql="SELECT * FROM chitietdonhang ";
class Chitietdonhang{
	function Chitietdonhang($id,$idDonHang,$user_id,$idSP,$TenSP,$HinhSP,$GiaSP,$SoLuong){
		$this->id=$id;
		$this->idDonHang=$idDonHang;
		$this->user_id=$user_id;
		$this->idSP=$idSP;
		$this->TenSP=$TenSP;
		$this->HinhSP=$HinhSP;
		$this->GiaSP=$GiaSP;
		$this->SoLuong=$SoLuong;
	}
}
$MangDonHang= array();
if (isset($_POST["user_id"])) {
		$user_id=$_POST["user_id"];
		$sql="SELECT * FROM chitietdonhang WHERE FIND_IN_SET('$user_id',user_id)";
		$dataSanPham=mysqli_query($connect,$sql);	
		$rowSanPham=mysqli_fetch_assoc($dataSanPham);
	}
	$data=mysqli_query($connect,$sql);	
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($MangDonHang, new Chitietdonhang($row["id"],$row["idDonHang"],$row["user_id"],$row["idSP"],$row["TenSP"],$row["HinhSP"],$row["GiaSP"],$row["SoLuong"]));
	}
	echo json_encode($MangDonHang);

 ?>