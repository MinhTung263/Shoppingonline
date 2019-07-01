<?php 
require_once "connect.php";
$sql="SELECT * FROM sanpham ORDER BY RAND()   ";
class SanPham{
	function SanPham($idSP,$TenSP,$GiaSP,$HinhSP,$MoTa,$idLoaiSP,$idDM){
		$this->idSP=$idSP;
		$this->TenSP=$TenSP;
		$this->GiaSP=$GiaSP;
		$this->HinhSP=$HinhSP;
		$this->MoTa=$MoTa;
		$this->idLoaiSP=$idLoaiSP;
		$this->idDM=$idDM;
	}
}
$MangSanPham= array();
	if (isset($_POST["idSP"])) {
		$idSP=$_POST["idSP"];
		$sql="SELECT * FROM sanpham WHERE FIND_IN_SET('$idSP',idSP)";
		$dataSanPham=mysqli_query($connect,$sql);	
		$rowSanPham=mysqli_fetch_assoc($dataSanPham);
	}
	if (isset($_POST["idDM"])) {
		$idDM=$_POST["idDM"];
		$sql="SELECT * FROM sanpham WHERE FIND_IN_SET('$idDM',idDM)";
		$dataSanPham=mysqli_query($connect,$sql);	
		$rowSanPham=mysqli_fetch_assoc($dataSanPham);
	}
	if (isset($_POST["idLoaiSP"])) {
		$idLoaiSP=$_POST["idLoaiSP"];
		$sql="SELECT * FROM sanpham WHERE FIND_IN_SET('$idLoaiSP',idLoaiSP)";
		$dataSanPham=mysqli_query($connect,$sql);	
		$rowSanPham=mysqli_fetch_assoc($dataSanPham);
	}
	$data=mysqli_query($connect,$sql);	
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($MangSanPham, new SanPham($row["idSP"],$row["TenSP"],$row["GiaSP"],$row["HinhSP"],$row["MoTa"],$row["idLoaiSP"],$row["idDM"]));
	}
	echo json_encode($MangSanPham);
 ?>