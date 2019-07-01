<?php 
require_once "connect.php";
	
class SanPham{
	function SanPham($idSP,$TenSP,$GiaSP,$HinhSP,$MoTa,$idLoaiSP){
		$this->idSP=$idSP;
		$this->TenSP=$TenSP;
		$this->GiaSP=$GiaSP;
		$this->HinhSP=$HinhSP;
		$this->MoTa=$MoTa;
		$this->idLoaiSP=$idLoaiSP;
	}
}
	$MangSanPham= array();
	if (isset($_POST["tukhoa"])) {
			$tukhoa=$_POST["tukhoa"];
			$sql="SELECT * FROM sanpham Where lower(TenSP) Like '%$tukhoa%' ";
			$data=mysqli_query($connect,$sql);	
		while ($row=mysqli_fetch_assoc($data)) {
			array_push($MangSanPham, new SanPham($row["idSP"],$row["TenSP"],$row["GiaSP"],$row["HinhSP"],$row["MoTa"],$row["idLoaiSP"]));
		}

	}
	echo json_encode($MangSanPham);
 ?>